package com.example.Service;

import com.example.Entity.Alert;

import java.util.List;

public interface AlertService {
    List<Alert>findAll();
    List<Alert>findAlertsByPriority(String priority);
    Alert create(Alert alert);
    List<Alert> findByVin(String vin);
}
