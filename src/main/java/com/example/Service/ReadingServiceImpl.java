package com.example.Service;

import com.example.Entity.Alert;
import com.example.Entity.Reading;
import com.example.Entity.Vehicle;
import com.example.Exception.ReadingNotFoundException;
import com.example.Repository.AlertRepo;
import com.example.Repository.ReadingsRepo;
import com.example.Repository.VehicleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class ReadingServiceImpl implements ReadingsService {
    @Autowired
    private  ReadingsRepo repo;
    @Autowired
    private VehicleRepo vehicleRepo;
    @Autowired
    private AlertRepo alertRepo;


    @Override
    @Transactional(readOnly = true)
    public List<Reading> findAll() {
        Iterable<Reading>readings = repo.findAll();
        List<Reading> result = new ArrayList<>();
        for(Reading read : readings){
            result.add(read);
        }
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reading> findReadingByVin(String vin) {
        Iterable<Reading> reading = repo.findReadingByVin(vin);
        List<Reading> result = new ArrayList<>();
        for(Reading reading1: reading){
            result.add(reading1);
        }
        if(result.size()==0){
            throw new ReadingNotFoundException("No Readings for Vin: "+vin);
        }
        return result;
    }

    @Override
    @Transactional
    public Reading create(Reading reading) {
        Optional<Vehicle> vehicle = vehicleRepo.findById(reading.getVin());
        if(vehicle.isPresent()){

            Date date = new Date();
            SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-DD hh:mm:ss.s");
            try {
                date = df.parse(reading.getTimestamp());
            }
            catch (ParseException e) {
                System.out.println(e.getMessage());
            }
            // alert
            Alert alert = new Alert();
            alert.setVin(reading.getVin());
            alert.setLatitude(reading.getLatitude());
            alert.setLongitude(reading.getLongitude());
            alert.setTimestamp(date);

            // tire pressure of any tire <32psi , Priority: LOW
            if(reading.getTires().getFrontLeft()<32 || reading.getTires().getFrontLeft()>36 || reading.getTires().getFrontRight()<32 || reading.getTires().getFrontRight()>36 || reading.getTires().getRearLeft()<32 || reading.getTires().getRearLeft()>36 || reading.getTires().getRearRight()<32 || reading.getTires().getRearRight()>36){
            alert.setDescription("Check Tire pressure");
            alert.setPriority("LOW");
            }
            // engineCooolantLow = true || checkEngineLightOn = true, Priority:LOW
            if(reading.isEngineCoolantLow() || reading.isCheckEngineLightOn()){
                alert.setDescription("Check Coolant / Check Engine light is on ");
                alert.setPriority("LOW");
            }
            // engineRPM>readLineRPM , Priority:LOW
            if(reading.getEngineRpm()>vehicle.get().getRedlineRpm()){

                alert.setPriority("HIGH");
                alert.setDescription("Engine RMP High");
            }
            // fuelVolume < 10% of maxFuelVolume, Priority:MEDIUM
            if(reading.getFuelVolume()<0.1*vehicle.get().getMaxFuelVolume()){
                alert.setPriority("MEDIUM");
                alert.setDescription("Low fuel level");
            }
            alertRepo.save(alert);
        }
        return repo.save(reading);
    }
}
