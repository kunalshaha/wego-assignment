package com.wego.parkingmanagement.dao;

import com.wego.parkingmanagement.exception.ParkingExceptionHandler;
import com.wego.parkingmanagement.model.CarParks;
import com.wego.parkingmanagement.model.ParkingMetaInfo;

import java.util.Map;
import java.util.List;

public interface ParkingDao {


    Map<String, ParkingMetaInfo> getParkingDataForLatLong() throws ParkingExceptionHandler;

    List<CarParks> getAvailableCarparks() throws ParkingExceptionHandler;
}
