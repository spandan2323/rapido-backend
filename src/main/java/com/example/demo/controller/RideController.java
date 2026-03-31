package com.example.demo.controller;

import com.example.demo.entity.Ride;
import com.example.demo.service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rides")
public class RideController {

    @Autowired
    private RideService rideService;

    @PostMapping("/book")
    public Ride bookRide(@RequestBody Ride ride){
        return rideService.bookRide(ride);

    }
    @PutMapping("/cancel/{rideId}")
    public Ride cancelRide(@PathVariable Long rideId){
        return rideService.cancelRide(rideId);
    }



    @GetMapping("/available")
    public List<Ride> getAvailableRides(){
        return rideService.getAvailableRides();
    }

    @PutMapping("/accept/{rideId}/{riderId}")
    public Ride acceptRide(@PathVariable Long rideId,
                           @PathVariable Long riderId){

        return rideService.acceptRide(rideId, riderId);
    }

    @GetMapping("/user/{userId}")
    public List<Ride> getUserRideHistory(@PathVariable Long userId){
        return rideService.getUserRideHistory(userId);
    }
    @PutMapping("/complete/{rideId}")
    public Ride completeRide(@PathVariable Long rideId){
        return rideService.completeRide(rideId);
    }
}