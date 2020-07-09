package com.example.Repository;

import com.example.Entity.Reading;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;
public interface ReadingsRepo extends CrudRepository<Reading,String> {

    Iterable<Reading> findReadingByVin(String vin);

}
