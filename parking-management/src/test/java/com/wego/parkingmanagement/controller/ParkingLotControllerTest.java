package com.wego.parkingmanagement.controller;


import com.wego.parkingmanagement.model.CarParks;
import com.wego.parkingmanagement.service.ParkingService;
import com.wego.parkingmanagement.service.ParkingServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ParkingLotController.class)
public class ParkingLotControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ParkingService parkingService;


    private final String jsonResp = "[{\"latitude\":1.29042987441557,\"longitude\":103.864681788302,\"total_lots\":100,\"available_lots\":50,\"carParkName\":\"Slot0\",\"address\":\"Bounavista\"},{\"latitude\":1.32157243060852,\"longitude\":103.884495486022,\"total_lots\":100,\"available_lots\":50,\"carParkName\":\"Slot1\",\"address\":\"Bounavista\"}]";

    @Test
    public void getNearestParkinList() throws Exception {
        String uri = "/carparks/nearest?latitude=1.1&longitude=1.23&page=1&per_page=3";
        when(parkingService.getAvailableCarParksForGivenLatAndLong(Mockito.anyDouble(), Mockito.anyDouble()
                , Mockito.anyInt(), Mockito.anyInt())).thenReturn(createMockCarParkList());
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(jsonResp, content);
    }


    @Test
    public void getNearestParkinListNegative() throws Exception {
        String uri = "/carparks/nearest?longitude=1.1&page=1&per_page=3";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(400, status);
    }


    private List<CarParks> createMockCarParkList() {
        List<Double> lats = new ArrayList<>();
        List<Double> longs = new ArrayList<>();
        lats.add(1.29042987441557);
        lats.add(1.32157243060852);
        longs.add(103.864681788302);
        longs.add(103.884495486022);

        List<CarParks> carParks = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            CarParks carPark = new CarParks();
            carPark.setLatitude(lats.get(i));
            carPark.setLongitude(longs.get(i));
            carPark.setTotal_lots(100);
            carPark.setAvailable_lots(50);
            carPark.setAddress("Bounavista");
            carPark.setCarParkName("Slot" + i);
            carParks.add(carPark);
        }
        return carParks;
    }
}
