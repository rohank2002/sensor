package com.example.Repository;

import com.example.Entity.Alert;
import org.springframework.data.repository.CrudRepository;

public interface AlertRepo extends CrudRepository<Alert, String> {
        Iterable<Alert> findByVin(String vin);
        Iterable<Alert>findByPriority(String priority);
}
