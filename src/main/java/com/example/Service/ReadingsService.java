package com.example.Service;

import com.example.Entity.Reading;

import java.util.List;

public interface ReadingsService {
    List<Reading> findAll();
    List<Reading> findReadingByVin(String vin);
    Reading create(Reading reading);
}
