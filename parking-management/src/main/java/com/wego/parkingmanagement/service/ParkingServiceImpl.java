package com.wego.parkingmanagement.service;

import com.wego.parkingmanagement.dao.ParkingDao;
import com.wego.parkingmanagement.exception.ParkingExceptionHandler;
import com.wego.parkingmanagement.model.CarParks;
import com.wego.parkingmanagement.utils.DistanceCalculator;
import com.wego.parkingmanagement.utils.PaginationUtlils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParkingServiceImpl implements ParkingService {

    @Autowired
    ParkingDao parkingDao;


    /**
     *
     * @param latitude
     * @param longitude
     * @param page
     * @param perPage
     * @return
     *
     */
    @Override
    public List<CarParks> getAvailableCarParksForGivenLatAndLong(double latitude, double longitude, Integer page, Integer perPage) {
        List<CarParks> availableParkingSlots = parkingDao.getAvailableCarparks();
        List<CarParks> nonNullSlots = availableParkingSlots.stream().filter(e->e!=null).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(nonNullSlots)){
            for (CarParks carPark : nonNullSlots) {
                //Calculate distance of the available carparks
                carPark.setCalculatedDistanceForGivenLatLong(DistanceCalculator.distance(latitude, longitude, carPark.getLatitude(), carPark.getLongitude(), "M"));
            }
            List<CarParks> parkingSlotsSortedByDistance = nonNullSlots.stream().sorted(Comparator.comparing(CarParks::getCalculatedDistanceForGivenLatLong)).collect(
              Collectors.toList());
            List<CarParks> paginatedSearchResult = PaginationUtlils.getPaginatedResult(page, perPage, parkingSlotsSortedByDistance);
            return paginatedSearchResult;
        }
        return  Collections.EMPTY_LIST;

    }


}


