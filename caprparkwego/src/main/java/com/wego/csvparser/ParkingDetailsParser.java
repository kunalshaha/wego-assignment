package com.wego.csvparser;

import com.wego.connection.JdbcConnect;
import com.wego.converter.LatLonCoordinate;
import com.wego.converter.SVY21;
import com.wego.pojo.ParkingInfo;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ParkingDetailsParser {

    /**
     * @param csvFile
     * @return Parses the CSV file fetching the relevant info
     */
  public static List<ParkingInfo> parseDataPushDataInDb(String csvFile) {

        List<ParkingInfo> infoList = new ArrayList<>();
        try (InputStream inputStream =
                     ParkingInfo.class.getClassLoader().getResourceAsStream(csvFile)) {
            CSVParser csvParser =
                    CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(new InputStreamReader(inputStream));
            List<CSVRecord> records = csvParser.getRecords();
            records.forEach(record -> {
                if (record.get(0) != null) {
                    String carparkName = record.get(0);
                    String adrress = record.get(1);
                    double latitude = Double.parseDouble(record.get(2));
                    double longitude = Double.parseDouble(record.get(3));
                    LatLonCoordinate conversionResult = SVY21.computeLatLon(latitude, longitude);
                    ParkingInfo parkingInfo = new ParkingInfo(carparkName, adrress, conversionResult.getLatitude(), conversionResult.getLongitude());
                    infoList.add(parkingInfo);
                }
            });

            createConnectionAndInsertDataInDB(infoList);

        } catch (Exception e) {
            System.out.println("Exception processing parseDataPushDataInDb"+e);
        }

        return infoList;
    }


    /**
     * @param parkingInfoList Injest the data in DB
     */
    private static void createConnectionAndInsertDataInDB(List<ParkingInfo> parkingInfoList) throws SQLException {
        Connection connection = JdbcConnect.getInstance().getConnectionObj();
        String queryForParkingDetails = " insert into parking_details(parking_name,parking_address,latitude,longitude)"
                + " values (?, ?, ?, ?)";
        try {
            PreparedStatement preparedStmtDataRec = connection.prepareStatement(queryForParkingDetails, Statement.RETURN_GENERATED_KEYS);

            for (int i = 0; i < parkingInfoList.size(); i++) {
                preparedStmtDataRec.setString(1, parkingInfoList.get(i).getCarparkName());
                preparedStmtDataRec.setString(2, parkingInfoList.get(i).getCarparkAddress());
                preparedStmtDataRec.setDouble(3, parkingInfoList.get(i).getLatitude());
                preparedStmtDataRec.setDouble(4, parkingInfoList.get(i).getLongitude());
                preparedStmtDataRec.addBatch();
            }
            preparedStmtDataRec.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            connection.close();
        }


    }
}
