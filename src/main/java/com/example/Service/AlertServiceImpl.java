package com.example.Service;

import com.example.Entity.Alert;
import com.example.Exception.AlertNotFoundException;
import com.example.Repository.AlertRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class AlertServiceImpl implements AlertService {
    @Autowired
    private AlertRepo alertRepo;
    @Override
    public List<Alert> findAll() {
        Iterable<Alert> alerts = alertRepo.findAll();

        List<Alert> result = new ArrayList<>();
        for(Alert temp : alerts){
            result.add(temp);
        }
        if(result.size()==0){
            throw  new AlertNotFoundException("Alerts have not been generated yet");
        }
        return result;
    }

    @Override
    public List<Alert> findAlertsByPriority(String priority) {
        List<Alert> result = new ArrayList<>();
        Iterable<Alert> temp = alertRepo.findByPriority(priority);
        //invalid priority
        for(Alert alert: temp){
            result.add(alert);
        }
        if(result.size()==0){
            throw  new AlertNotFoundException("No alerts found with "+priority+".");
        }
        return result;
    }

    @Override
    public Alert create(Alert alert) {
        return alertRepo.save(alert);
    }

    @Override
    public List<Alert> findByVin(String vin) {
        List<Alert> result  = new ArrayList<>();
        Iterable<Alert> temp = alertRepo.findByVin(vin);
        for(Alert alert: temp){
            result.add(alert);
        }
        if(result.size()==0){
            throw new AlertNotFoundException("No alerts found with vin "+vin+".");
        }
        return result;
    }
}
