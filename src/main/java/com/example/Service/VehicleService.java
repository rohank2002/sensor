package com.example.Service;

import com.example.Entity.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleService {
List<Vehicle> findAll();
Vehicle findOne(String vin);
Vehicle create(Vehicle vehicle);
List<Vehicle> update(List<Vehicle> vehicleList);
void delete(String vin);
}
