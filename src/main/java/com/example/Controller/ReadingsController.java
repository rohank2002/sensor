package com.example.Controller;

import com.example.Entity.Reading;
import com.example.Entity.Vehicle;
import com.example.Repository.AlertRepo;
import com.example.Repository.VehicleRepo;
import com.example.Service.ReadingsService;
import com.example.Service.VehicleService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/readings")
@CrossOrigin(origins = {"http://mocker.egen.io", "http://mocker.egen.academy"})
public class ReadingsController {
    @Autowired
    private ReadingsService readingsService;
    @Autowired
    private VehicleService vehicleService;

    @Autowired
    AlertRepo alertRepo;

    @PostMapping(consumes = "application/json",produces = "application/json")
    @ApiOperation(value = "Creates readings")
    @ApiResponses(value = {
            @ApiResponse(code =200,message = "OK"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public Reading create(@RequestBody Reading reading){
            return readingsService.create(reading);
    }
    @GetMapping(produces = "application/json")
    @ApiOperation(value = "Returns all the readings from Db")
    @ApiResponses(value = {
            @ApiResponse(code =200,message = "OK"),
            @ApiResponse(code = 404, message = "No readings available"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public List<Reading> findAll(){
        return  readingsService.findAll();
    }
    @GetMapping(path = "/vin/{vin}",produces = "application/json")
    @ApiOperation(value = "Returns all the readings with the requested priority")
    @ApiResponses(value = {
            @ApiResponse(code =200,message = "OK"),
            @ApiResponse(code = 404, message = "No readings found with requested priority"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public List<Reading> findReadingByVin(@ApiParam(value = "vin of the vehicle is required", required = true) @PathVariable("vin") String vin){
        return readingsService.findReadingByVin(vin);
    }
}
