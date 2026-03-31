package com.example.demo.repository;

import com.example.demo.entity.Rider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RiderRepository extends JpaRepository<Rider, Long> {

    List<Rider> findByAvailableTrue();

}