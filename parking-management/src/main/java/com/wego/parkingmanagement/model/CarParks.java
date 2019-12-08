package com.wego.parkingmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wego.parkingmanagement.validator.NumericString;


/**
 * This class stores the info regarding parking avalaibility
 */

public class CarParks implements Comparable<CarParks> {


    private double latitude;
    private double longitude;
    private Integer total_lots ;
    private Integer available_lots ;
    @JsonIgnore
    private Double calculatedDistanceForGivenLatLong;
    private String carParkName;
    private String address;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Integer getTotal_lots() {
        return total_lots;
    }

    public void setTotal_lots(Integer total_lots) {
        this.total_lots = total_lots;
    }

    public Integer getAvailable_lots() {
        return available_lots;
    }

    public void setAvailable_lots(Integer available_lots) {
        this.available_lots = available_lots;
    }

    public Double getCalculatedDistanceForGivenLatLong() {
        return calculatedDistanceForGivenLatLong;
    }

    public void setCalculatedDistanceForGivenLatLong(Double calculatedDistanceForGivenLatLong) {
        this.calculatedDistanceForGivenLatLong = calculatedDistanceForGivenLatLong;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCarParkName() {
        return carParkName;
    }

    public void setCarParkName(String carParkName) {
        this.carParkName = carParkName;
    }

    @Override
    public int compareTo(CarParks o) {
        return this.getCalculatedDistanceForGivenLatLong().compareTo(o.getCalculatedDistanceForGivenLatLong());
    }
}
