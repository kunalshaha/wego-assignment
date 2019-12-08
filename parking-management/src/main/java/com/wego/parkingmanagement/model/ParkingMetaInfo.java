package com.wego.parkingmanagement.model;

/**
 * This class maintains the static data
 */
public class ParkingMetaInfo {

    String parkingName;

    String parkingAdress;

    Double latitude;

    Double longitude;


    public ParkingMetaInfo() {
    }

    public ParkingMetaInfo(String parkingName, String parkingAdress, Double latitude, Double longitude) {
        this.parkingName = parkingName;
        this.parkingAdress = parkingAdress;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getParkingName() {
        return parkingName;
    }

    public void setParkingName(String parkingName) {
        this.parkingName = parkingName;
    }

    public String getParkingAdress() {
        return parkingAdress;
    }

    public void setParkingAdress(String parkingAdress) {
        this.parkingAdress = parkingAdress;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }


}
