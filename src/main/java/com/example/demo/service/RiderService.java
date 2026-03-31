package com.example.demo.service;

import com.example.demo.entity.Rider;
import com.example.demo.repository.RiderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RiderService {

    @Autowired
    private RiderRepository riderRepository;

    public Rider addRider(Rider rider){
        return riderRepository.save(rider);
    }

    public List<Rider> getAvailableRiders(){
        return riderRepository.findByAvailableTrue();
    }
}