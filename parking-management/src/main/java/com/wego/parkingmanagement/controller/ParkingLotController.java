package com.wego.parkingmanagement.controller;

import com.wego.parkingmanagement.model.CarParks;
import com.wego.parkingmanagement.service.ParkingService;
import io.swagger.annotations.*;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/carparks")
@Api(value = "Parking Management System", description = "Operations pertaining to parking slots in Parking Management System")
public class ParkingLotController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    ParkingService carParkService;

    @ApiOperation(value = "View a list of available parking slots nearby to the given co-ordinates", response = JSONArray.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 400, message = "If one of the param is missing"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })


    @GetMapping("/nearest")
    public ResponseEntity<List<CarParks>> getNearByAvailableParkingSlots(
            @ApiParam(value = "Latitude a needed co-ordiante to locate the parking slot")
            @RequestParam("latitude") double latitude,
            @ApiParam(value = "Longitude a needed co-ordiante to locate the parking slot")
            @RequestParam("longitude") double longitude,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "3") Integer per_page) {
       // List<CarParks> nearestCarParks = null;
        logger.info("Request for getNearByAvailableParkingSlots invoked");
        List<CarParks> nearestCarParks  = carParkService.getAvailableCarParksForGivenLatAndLong(latitude, longitude, page, per_page);
        logger.info("Request for getNearByAvailableParkingSlots Completed");
        return new ResponseEntity<List<CarParks>>(nearestCarParks, HttpStatus.OK);

    }

}
