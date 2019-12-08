package com.wego.pojo;

import org.codehaus.jackson.annotate.JsonProperty;

import java.sql.Timestamp;
import java.util.List;

public class CarParkData {
    @JsonProperty("carpark_info")
    List<CarParkInfo> carparkInfo;
    @JsonProperty("carpark_number")
    String carparkNumber;
    @JsonProperty("update_datetime")
    Timestamp updateDatetime;

    public CarParkData() {
    }

    public CarParkData(List<CarParkInfo> carparkInfonfo, String carparkNumber, Timestamp updateDatetime) {
        this.carparkInfo = carparkInfo;
        this.carparkNumber = carparkNumber;
        this.updateDatetime = updateDatetime;
    }


    public String getCarparkNumber() {
        return carparkNumber;
    }

    public Timestamp getUpdateDatetime() {
        return updateDatetime;
    }


    public void setCarparkNumber(String carparkNumber) {
        this.carparkNumber = carparkNumber;
    }

    public void setUpdateDatetime(Timestamp updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public List<CarParkInfo> getCarparkInfo() {
        return carparkInfo;
    }

    public void setCarparkInfo(List<CarParkInfo> carparkInfo) {
        this.carparkInfo = carparkInfo;
    }

    @Override
    public String toString() {
        return "CarParkData{" +
                "carparkInfo=" + carparkInfo +
                ", carparkNumber='" + carparkNumber + '\'' +
                ", updateDatetime=" + updateDatetime +
                '}';
    }
}
