package com.wego.parkingmanagement.service;

import com.wego.parkingmanagement.dao.ParkingDao;
import com.wego.parkingmanagement.model.CarParks;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ParkingServiceImplTest {

  @InjectMocks
  ParkingServiceImpl parkingService;

  @Mock
  ParkingDao parkingDao;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @After
  public void tearDown() {
    reset(parkingDao);
  }

  @Test
  public void testGetAvailableCarParksForGivenLatAndLong() {

    List<CarParks> availableParkingSlots = createMockCarParkList();
    when(parkingDao.getAvailableCarparks()).thenReturn(availableParkingSlots);
    List<CarParks> actualResult = parkingService
      .getAvailableCarParksForGivenLatAndLong(1.29042987441557, 103.92931968435, 1, 3);

    Assert.assertEquals(3, actualResult.size());
    Assert.assertEquals("Slot5", actualResult.get(0).getCarParkName());

    verify(parkingDao).getAvailableCarparks();
  }

  @Test
  public void testGetAvailableCarParksForGivenLatAndLongWithNoResult() {

    List<CarParks> emptyResults  = new ArrayList<>();
    when(parkingDao.getAvailableCarparks()).thenReturn(emptyResults);
    List<CarParks> actualResult = parkingService
      .getAvailableCarParksForGivenLatAndLong(0, 0, 1, 3);

    Assert.assertEquals(0, actualResult.size());
    verify(parkingDao).getAvailableCarparks();
  }

  private List<CarParks> createMockCarParkList() {

    List<Double> lats = new ArrayList<>();
    List<Double> longs = new ArrayList<>();

    lats.add(1.29042987441557);
    lats.add(1.32157243060852);
    lats.add(1.28086959938739);
    lats.add(1.27117099698707);
    lats.add(1.28289702628);
    lats.add(1.28658040495583);
    lats.add(1.28110336273659);

    longs.add(103.864681788302);
    longs.add(103.884495486022);
    longs.add(103.891726459201);
    longs.add(103.932270851459);
    longs.add(103.92931968435);
    longs.add(103.935597356286);
    longs.add(103.937978955143);

    List<CarParks> carParks = new ArrayList<>();

    for (int i = 0; i < 6; i++) {
      CarParks carPark = new CarParks();
      carPark.setLatitude(lats.get(i));
      carPark.setLongitude(longs.get(i));

      carPark.setCarParkName("Slot" + i);
      carParks.add(carPark);
    }

    return carParks;
  }
}


