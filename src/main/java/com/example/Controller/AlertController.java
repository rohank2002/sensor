package com.example.Controller;

import com.example.Entity.Alert;
import com.example.Service.AlertService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/alerts")
public class AlertController {
    @Autowired
    private AlertService alertService;

    @GetMapping(path = "/vin/{vin}",produces = "application/json")
    @ApiOperation(value = "Returns all the alerts pertaining to vin")
    @ApiResponses(value = {
            @ApiResponse(code =200,message = "OK"),
            @ApiResponse(code = 404, message = "No Alert found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public List<Alert> findAlertsByVin(@ApiParam(value = "vin required", required = true) @PathVariable("vin") String vin){
    return alertService.findByVin(vin);
    }

    @GetMapping(path = "/priority/{priority_type}",produces = "application/json")
    @ApiOperation(value = "Returns all the alerts with the requested priority")
    @ApiResponses(value = {
            @ApiResponse(code =200,message = "OK"),
            @ApiResponse(code = 404, message = "No Alert found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public List<Alert> findAlertsByPriority(@ApiParam(value = "priority of the alert is required", required = true) @PathVariable("priority_type") String priority){
        return  alertService.findAlertsByPriority(priority);
    }
}
