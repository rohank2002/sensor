package com.example.Service;

import com.example.Entity.Vehicle;
import com.example.Exception.VehicleNotFoundException;
import com.example.Repository.VehicleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class VehicleSeriveImpl implements VehicleService {
    @Autowired
    private VehicleRepo vehicleRepo;
    @Override
    public List<Vehicle> findAll() {
        List<Vehicle> result = new ArrayList<>();
        Iterable<Vehicle> temp = vehicleRepo.findAll();

        for(Vehicle v : temp){
            result.add(v);
        }
        if(result.size()==0){
            throw new VehicleNotFoundException("No vehicles registered yet");
        }
        return result;
    }

    @Override
    public Vehicle findOne(String vin) {
        Optional<Vehicle> v =  vehicleRepo.findByVin(vin);
        if(!v.isPresent()){
            throw new VehicleNotFoundException("No vehicle found with vin: "+vin+".");
        }
        return v.get();
    }

    @Override
    public Vehicle create(Vehicle vehicle) {
        return vehicleRepo.save(vehicle);
    }

    @Override
    public List<Vehicle> update(List<Vehicle> vehicleList) {
        Iterable<Vehicle> temp = vehicleRepo.saveAll(vehicleList);
        List<Vehicle> result = new ArrayList<>();
        for(Vehicle vehicle: temp){
            result.add(vehicle);
        }


        return result;
    }

    @Override
    public void delete(String vin) {
        if(!vehicleRepo.findById(vin).isPresent()){
            throw new VehicleNotFoundException("No vehicles found with vin: "+vin);
        }
        vehicleRepo.deleteById(vin);

    }
}
