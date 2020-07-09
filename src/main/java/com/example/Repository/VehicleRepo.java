package com.example.Repository;

import com.example.Entity.Vehicle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface VehicleRepo extends CrudRepository<Vehicle, String> {
        Optional<Vehicle> findByVin(String vin);
}
