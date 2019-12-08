package com.wego.main;

import com.wego.api.ExternalAPICaller;
import com.wego.csvparser.ParkingDetailsParser;

public class ExternalUtility {

    public static void main(String args[]) {

        System.out.println("Selection the operation to be performed");
        System.out.println("a. Load Static Data");
        System.out.println("b. Fetch Latest Parking Details from external api");

        switch (args[0]) {
            case "Load-Static-Data":
                System.out.println("Invoked static data to push");
                ParkingDetailsParser.parseDataPushDataInDb("hdb-carpark-information.csv");
                break;
            case "Load-API-Data":
                System.out.println("Invoked external Api");
                try {
                    ExternalAPICaller.getPCarParkAvailabilityRESTAPI();
                } catch (Exception e) {
                    System.out.println("Exception in processing External API" + e);
                }
                break;
            default:
                System.out.println("Please select valid option");
                break;
        }
    }

}
