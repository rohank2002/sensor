package com.example.Controller;

import com.example.Entity.Vehicle;
import com.example.Service.VehicleService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping ("/vehicles")
@CrossOrigin(origins = {"http://mocker.egen.io", "http://mocker.egen.academy"})
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;
    @PutMapping(consumes = "application/json",produces = "application/json")
    @ApiOperation(value = "Provide a list of vehicles")
    @ApiResponses(value = {
            @ApiResponse(code =200,message = "OK"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public List<Vehicle> update(@RequestBody  List<Vehicle> vehicleList){
    Iterable<Vehicle> vehicles = vehicleService.update(vehicleList);
    List<Vehicle> result = new ArrayList<>();
    for(Vehicle v : vehicles){
        result.add(v);
    }
    return result;
    }
    @GetMapping(produces = "application/json")
    @ApiOperation(value = "Returns all vehicles from Db")
    @ApiResponses(value = {
            @ApiResponse(code =200,message = "OK"),
            @ApiResponse(code = 404, message = "No vehicles found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public List<Vehicle> findAll(){
        return vehicleService.findAll();
    }
    @GetMapping(produces = "application/json",path = "/vin/{vin}")
    @ApiOperation(value = "Returns a single vehicle based on vin")
    @ApiResponses(value = {
            @ApiResponse(code =200,message = "OK"),
            @ApiResponse(code = 404, message = "No vehicles found with requested vin"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public Vehicle findOne(@ApiParam(value = "vin of the vehicle is required", required = true) @PathVariable("vin") String vin){
    return vehicleService.findOne(vin);
    }



}
