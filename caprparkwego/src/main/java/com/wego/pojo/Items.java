package com.wego.pojo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Items{


    /*private Date timestamp;

    private List<Carpark_data> carpark_data;

    public void setTimestamp(Date timestamp){
        this.timestamp = timestamp;
    }
    public Date getTimestamp(){
        return this.timestamp;
    }
    public void setCarpark_data(List<Carpark_data> carpark_data){
        this.carpark_data = carpark_data;
    }
    public List<Carpark_data> getCarpark_data(){
        return this.carpark_data;
    }

    @Override
    public String toString() {
        return "Items{" +
                "timestamp='" + timestamp + '\'' +
                ", carpark_data=" + carpark_data +
                '}';
    }*/

     @JsonProperty("timestamp")
    Date timestamp;
    @JsonProperty("carpark_data")
    List<CarParkData> carParkData;

    public Items() {
    }

    public Items(Date timestamp, List<CarParkData> carParkData) {
        this.timestamp = timestamp;
        this.carParkData = carParkData;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public List<CarParkData> getCarParkData() {
        return carParkData;
    }

    public void setCarParkData(List<CarParkData> carParkData) {
        this.carParkData = carParkData;
    }

    @Override
    public String toString() {
        return "Items{" +
                "timestamp=" + timestamp +
                ", carParkData=" + carParkData +
                '}';
    }
}
