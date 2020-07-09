package com.example.Service;

import com.example.Entity.Alert;
import com.example.Entity.Reading;
import com.example.Entity.Tires;
import com.example.Exception.ReadingNotFoundException;
import com.example.Repository.AlertRepo;
import com.example.Repository.ReadingsRepo;
import com.example.Repository.VehicleRepo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
public class ReadingServiceImplTest {
    @TestConfiguration
    static class ReadingServiceImplTestConfig {
        @Bean
        public ReadingsService getReadingService() {
            return new ReadingServiceImpl();
        }
    }

    @MockBean
    private ReadingsRepo readingsRepo;
    @MockBean
    private VehicleRepo vehicleRepo;
    @MockBean
    private AlertRepo alertRepo;

    @Autowired
    private ReadingsService readingsService;
    private List<Reading> readingList;
    private Iterable<Reading> readingIterable;
    private Reading reading;
    private Iterable<Reading> readingIterableByVin;
    private Alert alert;

    @Before
    public void setup() {

        createReadingList();
        Mockito.when(readingsRepo.findAll())
                .thenReturn(readingIterable);

        Mockito.when(readingsRepo.save(reading))
                .thenReturn(reading);
        fillReadingIterableVin();

        Mockito.when(readingsRepo.findReadingByVin(readingList.get(0).getVin()))
                .thenReturn(readingIterableByVin);

        Mockito.when(alertRepo.save(alert))
                .thenReturn(alert);

    }


    @Test
    public void findAll() {
        List<Reading> readingResult = readingsService.findAll();
        Assert.assertEquals("List of readings should match",readingList,readingResult);
    }

    @Test
    public void findReadingByVin() {
        List<Reading> readingListResult = readingsService.findReadingByVin(readingList.get(0).getVin());
        Assert.assertEquals("Reading list should match",readingListResult,readingList);
    }

    @Test(expected = ReadingNotFoundException.class)
    public void findReadingByVinNotFound() {
        List<Reading> readingListResult = readingsService.findReadingByVin("123");
    }

    @Test
    public void createReading() {
    Reading readingResult = readingsRepo.save(reading);
    Assert.assertEquals("reading should match",reading,readingResult);
    }

    private void createReadingList() {
        alert = new Alert();
        readingList = new ArrayList<>();
        Reading reading1 = new Reading();
        reading1.setVin("1HGCR2F3XFA027534");
        reading1.setLatitude(41.803194);
        reading1.setLongitude(-88.144406);
        reading1.setTimestamp(getDate("2017-05-25T17:31:25.268Z"));
        reading1.setFuelVolume(1.5);
        reading1.setSpeed(85);
        reading1.setEngineHp(240);
        reading1.setCheckEngineLightOn(false);
        reading1.setEngineCoolantLow(true);
        reading1.setCruiseControlOn(true);
        reading1.setEngineRpm(6300);
        Tires tires = new Tires();
        tires.setFrontLeft(34);
        tires.setFrontRight(35);
        tires.setRearLeft(33);
        tires.setRearRight(34);
        reading1.setTires(tires);

        // Generate medium
        Reading reading2 = new Reading();
        reading2.setVin("2ZGCR2F3XFA072534");
        reading2.setLatitude(21.803194);
        reading2.setLongitude(-28.144406);
        reading2.setTimestamp(getDate("2019-07-15T17:31:25.268Z"));
        reading2.setFuelVolume(1.5);
        reading2.setSpeed(85);
        reading2.setEngineHp(240);
        reading2.setCheckEngineLightOn(true);
        reading2.setEngineCoolantLow(false);
        reading2.setCruiseControlOn(true);
        reading2.setEngineRpm(6300);
        tires.setFrontLeft(34);
        tires.setFrontRight(36);
        tires.setRearLeft(33);
        tires.setRearRight(34);
        reading2.setTires(tires);

        // Generate alert as low
        Reading reading3 = new Reading();
        reading3.setVin("WP1AB29P63LA60179");
        reading3.setLatitude(20.803194);
        reading3.setLongitude(28.144406);
        reading3.setTimestamp(getDate("2013-05-25T17:31:25.268Z"));
        reading3.setFuelVolume(1.5);
        reading3.setSpeed(85);
        reading3.setEngineHp(280);
        reading3.setCheckEngineLightOn(false);
        reading3.setEngineCoolantLow(false);
        reading3.setCruiseControlOn(false);
        reading3.setEngineRpm(3000);
        tires.setFrontLeft(12);
        tires.setFrontRight(36);
        tires.setRearLeft(29);
        tires.setRearRight(34);
        reading3.setTires(tires);

        readingList.add(reading1);
        readingList.add(reading2);
        readingList.add(reading3);
        readingIterable = readingList;
        reading = readingList.get(0);

        alert.setVin("1HGCR2F3XFA127534");
        alert.setLongitude(12.803194);
        alert.setLatitude(1.803194);
        alert.setTimestamp(getDate("2018-12-25T17:31:25.268Z"));
        alert.setDescription("High RPM");
        alert.setPriority("High");
    }

    private Date getDate(String d) {


        DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_DATE_TIME;
        TemporalAccessor accessor = timeFormatter.parse(d);
        Date date = Date.from(Instant.from(accessor));


        return date;
    }

    private void fillReadingIterableVin(){
        Reading reading = readingList.get(0);
        readingList.clear();
        readingList.add(reading);
        readingIterableByVin = readingList;
    }
}