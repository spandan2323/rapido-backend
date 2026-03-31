package com.example.demo.controller;

import com.example.demo.entity.Rider;
import com.example.demo.service.RiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/riders")
public class RiderController {

    @Autowired
    private RiderService riderService;

    @PostMapping("/add")
    public Rider addRider(@RequestBody Rider rider){
        return riderService.addRider(rider);
    }

    @GetMapping("/available")
    public List<Rider> getAvailableRiders(){
        return riderService.getAvailableRiders();
    }
}