package com.example.Entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Vehicle {
    /*
    {
    "vin": "1HGCR2F3XFA027534",
    "make": "HONDA",
    "model": "ACCORD",
    "year": 2015,
    "redlineRpm": 5500,
    "maxFuelVolume": 15,
    "lastServiceDate": "2017-05-25T17:31:25.268Z"
 }
     */
    @Id
    @Column(columnDefinition = "VARCHAR(17)")
    private String vin;
    @Column(columnDefinition = "VARCHAR(16)")
    private String make;
    @Column(columnDefinition = "VARCHAR(16)")
    private String model;
    @Column(columnDefinition = "VARCHAR(4)")
    private int year;
    @Column(columnDefinition = "INTEGER(4)")
    private int  redlineRpm;

    @Column(columnDefinition = "DATETIME")
    @DateTimeFormat(pattern = "YYYY-MM-DDThh:mm:ss.sTZD")
    private Date lastServiceDate;
    @Column(columnDefinition = "VARCHAR(3)")
    private int maxFuelVolume;

    public int getMaxFuelVolume() {
        return maxFuelVolume;
    }

    public void setMaxFuelVolume(int maxFuelVolume) {
        this.maxFuelVolume = maxFuelVolume;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getVin() {
        return vin;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getRedlineRpm() {
        return redlineRpm;
    }

    public void setRedlineRpm(int redlineRpm) {
        this.redlineRpm = redlineRpm;
    }

    public Date getLastServiceDate() {
        return lastServiceDate;
    }

    public void setLastServiceDate(Date lastServiceDate) {
        this.lastServiceDate = lastServiceDate;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vin='" + vin + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", redlineRpm=" + redlineRpm +
                ", lastServiceDate=" + lastServiceDate +
                ", maxFuelVolume=" + maxFuelVolume +
                '}';
    }
}
