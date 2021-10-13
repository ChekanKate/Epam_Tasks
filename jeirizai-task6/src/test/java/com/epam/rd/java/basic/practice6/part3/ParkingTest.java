package com.epam.rd.java.basic.practice6.part3;
import org.junit.*;

public class ParkingTest {
    @Test
    public void testArrive() {
        Parking parking = new Parking(4);
        int place1 = 2;
        boolean expectedResult = true;
        boolean result = parking.arrive(place1);
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void testDepart() {
        Parking parking = new Parking(4);
        int place1 = 2;
        boolean expectedResult = false;
        boolean result = parking.depart(place1);
        Assert.assertEquals(expectedResult, result);
    }

}