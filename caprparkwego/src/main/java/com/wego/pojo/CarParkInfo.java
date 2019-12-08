package com.wego.pojo;


import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CarParkInfo {

    public CarParkInfo() {
    }

    @JsonProperty("total_lots")
    int totalLots;
    @JsonProperty("lot_type")
    char lotType;
    @JsonProperty("lots_available")
    int lotsAvailable;

    public CarParkInfo(int totalLots, char lotType, int lotsAvailable) {
        this.totalLots = totalLots;
        this.lotType = lotType;
        this.lotsAvailable = lotsAvailable;
    }

    public int getTotalLots() {
        return totalLots;
    }

    public void setTotalLots(int totalLots) {
        this.totalLots = totalLots;
    }

    public char getLotType() {
        return lotType;
    }

    public void setLotType(char lotType) {
        this.lotType = lotType;
    }

    public int getLotsAvailable() {
        return lotsAvailable;
    }

    public void setLotsAvailable(int lotsAvailable) {
        this.lotsAvailable = lotsAvailable;
    }

    @Override
    public String toString() {
        return "CarParkInfo{" +
                "totalLots=" + totalLots +
                ", lotType=" + lotType +
                ", lotsAvailable=" + lotsAvailable +
                '}';
    }
}
