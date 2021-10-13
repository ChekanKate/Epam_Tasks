package com.epam.rd.java.basic.practice6.part4;
 import org.junit.*;

public class RangeTest {

    @Test
    public void testRange() {
        Range r = new Range(3, 10);
        String result = "3 4 5 6 7 8 9 10 ";
        String expectedResult = new String("");
        for (Integer el : r) {
            expectedResult = expectedResult.concat(String.valueOf(el) + " ");
        }

        Assert.assertEquals(result, expectedResult);
    }
}
