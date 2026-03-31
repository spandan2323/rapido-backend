package com.example.demo.service;

import com.example.demo.entity.Ride;
import com.example.demo.entity.RideStatus;
import com.example.demo.repository.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RideService {

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private NotificationService notificationService;

    // BOOK RIDE
    public Ride bookRide(Ride ride) {

        double cost;

        if (ride.getBookingType().equalsIgnoreCase("PREBOOK")) {
            cost = ride.getDistance() * 15;
        } else {
            cost = ride.getDistance() * 10;
        }

        ride.setCost(cost);
        ride.setStatus(RideStatus.PENDING);

        Ride savedRide = rideRepository.save(ride);

        // Send notification
        notificationService.sendNotification(
                ride.getUserId(),
                "Ride booked successfully. Waiting for rider acceptance."
        );

        return savedRide;
    }

    // CANCEL RIDE
    public Ride cancelRide(Long rideId) {

        Ride ride = rideRepository.findById(rideId)
                .orElseThrow(() -> new RuntimeException("Ride not found"));

        LocalDateTime now = LocalDateTime.now();

        Duration duration = Duration.between(now, ride.getRideDate());

        if (duration.toMinutes() <= 30) {

            double penalty = ride.getCost() * 0.10;

            ride.setPenalty(penalty);
        }

        if (ride.getBookingType().equalsIgnoreCase("PREBOOK")) {

            double refund = ride.getCost() * 0.80;

            ride.setRefund(refund);
        }

        ride.setStatus(RideStatus.CANCELLED);

        Ride updatedRide = rideRepository.save(ride);

        // Send notification
        notificationService.sendNotification(
                ride.getUserId(),
                "Ride cancelled. Penalty: ₹" + ride.getPenalty() +
                        ", Refund: ₹" + ride.getRefund()
        );

        return updatedRide;
    }


    public Ride acceptRide(Long rideId, Long riderId){

        Ride ride = rideRepository.findById(rideId)
                .orElseThrow(() -> new RuntimeException("Ride not found"));

        ride.setRiderId(riderId);
        ride.setStatus(RideStatus.ACCEPTED);

        Ride updatedRide = rideRepository.save(ride);

        notificationService.sendNotification(
                ride.getUserId(),
                "Rider accepted your trip."
        );

        return updatedRide;
    }

    public List<Ride> getAvailableRides(){
        return rideRepository.findByStatus("REQUESTED");
    }

    public List<Ride> getUserRideHistory(Long userId){
        return rideRepository.findByUserId(userId);
    }

    public Ride completeRide(Long rideId){

        Ride ride = rideRepository.findById(rideId)
                .orElseThrow(() -> new RuntimeException("Ride not found"));

        ride.setStatus(RideStatus.COMPLETED);

        Ride updatedRide = rideRepository.save(ride);

        notificationService.sendNotification(
                ride.getUserId(),
                "Your ride has been completed successfully."
        );

        return updatedRide;
    }
    }
