package com.wego.api;

import com.wego.connection.JdbcConnect;
import com.wego.pojo.CarParkData;
import com.wego.pojo.CarParkInfo;
import com.wego.pojo.ItemList;
import com.wego.pojo.Items;
import com.wego.util.PropertyLoader;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;


public class ExternalAPICaller {

    private static final String EXTERNALURI = PropertyLoader.loadProperties().getProperty("api.exertnal.uri");


    /**
     *
     * @throws Exception
     *
     *
     */
    public static void getPCarParkAvailabilityRESTAPI() throws Exception {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        try {
            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            Date d = new Date(cal.getTimeInMillis());
            SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            String formattedDate = input.format(d);
            System.out.println("Executing API for TS ::  "+formattedDate);

            HttpGet getRequest = new HttpGet(EXTERNALURI + formattedDate);

            //Set the API media type in http accept header
            getRequest.addHeader("accept", "application/JSON");

            //Send the request; It will immediately return the response in HttpResponse object
            HttpResponse response = httpClient.execute(getRequest);

            //verify the valid error code first
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
               System.out.println("Failed with HTTP error code :: "+statusCode);
                throw new RuntimeException("Failed with HTTP error code : " + statusCode);
            }

            HttpEntity httpEntity = response.getEntity();
            String apiOutput = EntityUtils.toString(httpEntity);
            ObjectMapper mapper = new ObjectMapper();
            ItemList itemList = mapper.readValue(apiOutput, ItemList.class);
            updateOrInsertDataAccordingToLatestInfo(itemList.getItems());


        } finally {
            //Important: Close the connect
            httpClient.getConnectionManager().shutdown();
        }
    }


    /**
     *
     * @param itmesToUpdateOrInsert
     * @throws SQLException
     *
     * Method to get the connection for the DB and Upsert the latest data
     *
     */
     static void updateOrInsertDataAccordingToLatestInfo(List<Items> itmesToUpdateOrInsert) throws SQLException {
        Connection connection = JdbcConnect.getInstance().getConnectionObj();
        String queryForCarParkAvailaibility1 = "INSERT INTO car_park_info (car_park_number,lot_type,total_lots,lots_available,update_datetime)" +
                " VALUES (?,?,?,?,?)" +
                "ON CONFLICT(car_park_number,lot_type)" +
                " DO UPDATE SET total_lots=EXCLUDED.total_lots,lots_available=EXCLUDED.lots_available,update_datetime=EXCLUDED.update_datetime";
        try {
            PreparedStatement preparedStmtDataRec = connection.prepareStatement(queryForCarParkAvailaibility1, Statement.NO_GENERATED_KEYS);
            for (int i = 0; i < itmesToUpdateOrInsert.size(); i++) {
                List<CarParkData> carParkDataList = itmesToUpdateOrInsert.get(i).getCarParkData();
                for (int j = 0; j < carParkDataList.size(); j++) {
                    String carparkName = carParkDataList.get(j).getCarparkNumber();
                    CarParkInfo carParkInfo = carParkDataList.get(j).getCarparkInfo().get(0);

                    preparedStmtDataRec.setString(1, carparkName);
                    preparedStmtDataRec.setString(2, carParkInfo.getLotType() + "");
                    preparedStmtDataRec.setInt(3, carParkInfo.getTotalLots());
                    preparedStmtDataRec.setInt(4, carParkInfo.getLotsAvailable());
                    preparedStmtDataRec.setTimestamp(5, carParkDataList.get(j).getUpdateDatetime(),
                            Calendar.getInstance(TimeZone.getTimeZone("UTC")));
                    preparedStmtDataRec.addBatch();

                }
            }
            preparedStmtDataRec.executeBatch();
        } catch (Exception e) {
          System.out.println("Error in processing updateOrInsertDataAccordingToLatestInfo ");

        } finally {
            connection.close();
        }
    }
}
