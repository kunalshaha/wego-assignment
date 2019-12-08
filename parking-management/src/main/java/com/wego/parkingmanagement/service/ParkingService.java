package com.wego.parkingmanagement.service;

import com.wego.parkingmanagement.model.CarParks;

import java.util.List;

public interface ParkingService {
    public List<CarParks> getAvailableCarParksForGivenLatAndLong(double latitude, double longitude,Integer page, Integer perPage);
}
