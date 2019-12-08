package com.wego.parkingmanagement.dao;

import com.wego.parkingmanagement.exception.ParkingExceptionHandler;
import com.wego.parkingmanagement.model.CarParks;
import com.wego.parkingmanagement.model.ParkingMetaInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ParkingDaoImpl extends JdbcDaoSupport implements ParkingDao {
    @Autowired
    DataSource dataSource;


    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());



    /**
     * @return This method Get all the static parking data
     */
    @Override
    public Map<String, ParkingMetaInfo> getParkingDataForLatLong() {
        logger.error("Started execution of getAvailableCarparks :: ");

        String sql = "SELECT * FROM parking_details";
        try {
            HashMap<String, ParkingMetaInfo> results = new HashMap<>();
            return getJdbcTemplate().query(sql, (ResultSet rs) -> {
                while (rs.next()) {
                    results.put(rs.getString("parking_name")
                            , new ParkingMetaInfo(rs.getString("parking_name")
                                    , rs.getString("parking_address"), rs.getDouble("latitude"),
                                    rs.getDouble("longitude")));
                }
                return results;
            });
        }catch (Exception e){
            logger.error("Error processing getParkingDataForLatLong :: ",e);
            throw new ParkingExceptionHandler("Internal Error occured");
        }

    }


    /**
     * @return This method gets the available car park from DB and Joins the
     * same with static data to get Lat,Long value.
     */
    @Override
    public List<CarParks> getAvailableCarparks() throws ParkingExceptionHandler {
        logger.error("Started execution of getAvailableCarparks :: ");

        String sql = "SELECT * from car_park_info where lots_available > 0";
        try {
            Map<String, ParkingMetaInfo> parkingMetaInfos = getParkingDataForLatLong();
            return getJdbcTemplate().query(sql, new RowMapper<CarParks>() {
                @Override
                public CarParks mapRow(ResultSet rs, int rwNumber) throws SQLException {
                    CarParks casrParkingAvailability = new CarParks();
                    casrParkingAvailability.setCarParkName(rs.getString("car_park_number"));
                    casrParkingAvailability.setTotal_lots(rs.getInt("total_lots"));
                    casrParkingAvailability.setAvailable_lots(rs.getInt("lots_available"));
                    if (parkingMetaInfos.get(rs.getString("car_park_number")) != null) {
                        ParkingMetaInfo parkingMetaInfo = parkingMetaInfos.get(rs.getString("car_park_number"));
                        casrParkingAvailability.setLatitude(parkingMetaInfo.getLatitude());
                        casrParkingAvailability.setLongitude(parkingMetaInfo.getLongitude());
                        casrParkingAvailability.setAddress(parkingMetaInfo.getParkingAdress());
                    } else {
                        casrParkingAvailability = null;
                    }
                    return casrParkingAvailability;
                }
            });
        }catch (Exception e){
            logger.error("Error processing getAvailableCarparks :: ",e);
            throw new ParkingExceptionHandler("Internal Error occured");
        }

    }
}
