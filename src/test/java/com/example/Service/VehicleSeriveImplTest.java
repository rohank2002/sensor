package com.example.Service;

import com.example.Entity.Vehicle;
import com.example.Exception.VehicleNotFoundException;
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
public class VehicleSeriveImplTest {
    @TestConfiguration
    static class VehicleSeriveImplTestConfig{
        @Bean
        public VehicleService getVehicleService(){
            return new VehicleSeriveImpl();
        }
    }
    @MockBean
    private VehicleRepo vehicleRepo;
    @Autowired
    private  VehicleService vehicleService;

    private List<Vehicle> vehicleList;

    private Vehicle vehicle;



    private Iterable<Vehicle> vehicleIterable;
    @Before
    public void setup(){
    filler();
    vehicle = vehicleList.get(0);
        Mockito.when(vehicleRepo.findAll())
                .thenReturn(vehicleIterable);

        Mockito.when(vehicleRepo.findByVin(vehicleList.get(0).getVin()))
                .thenReturn(Optional.of(vehicleList.get(0)));

        Mockito.when(vehicleRepo.saveAll(vehicleList))
                .thenReturn(vehicleIterable);

        Mockito.when(vehicleRepo.save(vehicle))
                .thenReturn(vehicle);

//        Mockito.verify(vehicleRepo).deleteById(vehicleList.get(0).getVin());



    }

    @Test
    public void findAll() {
    List<Vehicle> result = vehicleService.findAll();
    Assert.assertEquals("list of vehicles should match",vehicleList,result);
    }

    @Test(expected = VehicleNotFoundException.class)
    public void findAllNotFound() {
        vehicleList.clear();
        vehicleIterable = vehicleList;
        List<Vehicle> result = vehicleService.findAll();
    }



    @Test
    public void findOne() {
        Vehicle v = vehicleService.findOne("1HGCR2F3XFA027534");
        Assert.assertEquals("Vehicles should match",vehicle,v);
    }

    @Test(expected = VehicleNotFoundException.class)
    public void findOneNotFound() {
        Vehicle v = vehicleService.findOne("123");

    }



    @Test
    public void create() {
        Vehicle v = vehicleService.create(vehicle);
        Assert.assertEquals("Vehicle should match", vehicle,v);
    }

    @Test
    public void update() {
        List<Vehicle> vlist = vehicleService.update(vehicleList);
        Assert.assertEquals("Vehicle list should match",vehicleList,vlist);
    }

    @Test
    public void delete() {
//        Mockito.verify(vehicleRepo).deleteById(vehicleList.get(0).getVin());
    }

    private void filler(){
    Vehicle v1 = new Vehicle();
    Vehicle v2 = new Vehicle();
    Vehicle v3  = new Vehicle();
     vehicleList = new ArrayList<>();
        v1.setVin("1HGCR2F3XFA027534");
        v1.setMake("HONDA");
        v1.setModel("ACCORD");
        v1.setYear(2015);
        v1.setRedlineRpm(5500);
        v1.setMaxFuelVolume(15);
        v1.setLastServiceDate(getDate("2017-05-25T17:31:25.268Z"));

        v2.setVin("WP1AB29P63LA60179");
        v2.setMake("PORSCHE");
        v2.setModel("CAYENNE");
        v2.setYear(2015);
        v2.setRedlineRpm(6000);
        v2.setMaxFuelVolume(18);
        v2.setLastServiceDate(getDate("2017-03-25T17:31:25.268Z"));

        v3.setVin("AB1AB29P63LA60172");
        v3.setMake("Aston Matrin");
        v3.setModel("DBS");
        v3.setYear(2018);
        v3.setRedlineRpm(7000);
        v3.setMaxFuelVolume(20);
        v3.setLastServiceDate(getDate("2019-07-15T17:31:25.268Z"));

    vehicleList.add(v1);
    vehicleList.add(v2);
    vehicleList.add(v3);
    vehicleIterable = vehicleList;
    vehicle = vehicleList.get(0);
    }

    private Date getDate(String d) {


        DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_DATE_TIME;
        TemporalAccessor accessor = timeFormatter.parse(d);
        Date date = Date.from(Instant.from(accessor));


        return date;
    }
}