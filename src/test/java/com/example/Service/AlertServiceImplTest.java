package com.example.Service;

import com.example.Entity.Alert;
import com.example.Exception.AlertNotFoundException;
import com.example.Repository.AlertRepo;

import jdk.nashorn.internal.runtime.options.Option;
import org.junit.After;
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
import java.util.Optional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.*;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
public class AlertServiceImplTest {
    @TestConfiguration
    static class AlertServiceImplTestConfig{
        @Bean
        public AlertService getAlertService(){
            return  new AlertServiceImpl();
        }

    }
    @Autowired
    private AlertService alertService;

    @MockBean
    private AlertRepo alertRepo;

    private List<Alert> alerts;
    private Iterable<Alert> alertIterable;

    private Alert alert;
    @Before
    public void setup(){
        alerts = new ArrayList<>();
        fillAlerts();
        alertIterable=alerts;
        Mockito.when(alertRepo.findAll())
                .thenReturn(alertIterable);


        Mockito.when(alertRepo.findByPriority(alerts.get(0).getPriority()))
                .thenReturn(alertIterable);
        setAlert();
        Mockito.when(alertRepo.save(alert))
                .thenReturn(alert);


        Mockito.when(alertRepo.findByVin(alerts.get(0).getVin()))
                .thenReturn(alertIterable);


    }

    private void setAlert(){
        alert = new Alert();
        alert.setVin("1HGCR2F3XFA127534");
        alert.setLongitude(12.803194);
        alert.setLatitude(1.803194);
        alert.setTimestamp(getDate("2018-12-25T17:31:25.268Z"));
        alert.setDescription("High RPM");
        alert.setPriority("High");
    }


    private Date getDate(String d){


        DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_DATE_TIME;
        TemporalAccessor accessor = timeFormatter.parse(d);
        Date date = Date.from(Instant.from(accessor));


        return date;

    }


    @Test
    public void findAll() {
    List<Alert> result = alertService.findAll();
        Assert.assertEquals("alerts should match",alerts,result);
    }

    @Test(expected = AlertNotFoundException.class)
    public void findAllNoneFound(){
        alerts.clear();
        alertIterable = alerts;
        Mockito.when(alertRepo.findAll())
                .thenReturn(alertIterable);
        List<Alert> result = alertService.findAll();
    }

    @Test
    public void findAlertsByPriority() {
        List<Alert> alertBypriorityList  = new ArrayList<>();
        alertBypriorityList.add(alerts.get(0));
        alerts.clear();
        alerts.add(alertBypriorityList.get(0));
        alertIterable=alerts;
        List<Alert> result = alertService.findAlertsByPriority("LOW");

        Assert.assertEquals("Alerts by priority",alertIterable,result);
    }

    @Test(expected = AlertNotFoundException.class)
    public void findAlertsByPriorityNoneFound() {


        List<Alert> result = alertService.findAlertsByPriority("abc");

    }



    @Test
    public void create() {

        Assert.assertEquals("alert created",alert,alertService.create(alert));
    }

    @Test
    public void findByVin() {
        Alert a = alerts.get(0);
        alerts.clear();
        System.out.println(alerts);
        alerts.add(a);
        alertIterable = alerts;
        List<Alert> result = alertService.findByVin(a.getVin());
        Assert.assertEquals("alert by vin",alertIterable,result);
    }

    @Test(expected = AlertNotFoundException.class )
    public void findByVinNoneFound() {
        List<Alert> temp = alertService.findByVin("aa");

    }

    public void fillAlerts(){
        Alert alert1 = new Alert();

        alert1.setVin("1HGCR2F3XFA027534");
        alert1.setLongitude(41.803194);
        alert1.setLatitude(-88.144406);
        alert1.setTimestamp(getDate("2017-05-25T17:31:25.268Z"));
        alert1.setDescription("Tire issue");
        alert1.setPriority("LOW");

        Alert alert2 = new Alert();
        alert2.setVin("1HGCR2F3XFA027234");
        alert2.setLongitude(1.803194);
        alert2.setLatitude(-20.144406);
        alert2.setTimestamp(getDate("2016-03-15T17:31:25.268Z"));
        alert2.setDescription("Low coolant");
        alert2.setPriority("Medium");

        Alert alert3 = new Alert();
        alert3.setVin("1HGCR2F3XFA127534");
        alert3.setLongitude(12.803194);
        alert3.setLatitude(1.803194);
        alert3.setTimestamp(getDate("2018-12-25T17:31:25.268Z"));
        alert3.setDescription("High RPM");
        alert3.setPriority("High");
        alerts.add(alert1);
        alerts.add(alert2);
        alerts.add(alert3);
    }
}