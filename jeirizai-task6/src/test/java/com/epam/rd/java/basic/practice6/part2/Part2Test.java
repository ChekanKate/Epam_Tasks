package com.epam.rd.java.basic.practice6.part2;

import org.junit.Assert;
import org.junit.Test;

public class Part2Test {
    @Test
    public void arrayListIndexTest() {
        Part2 p = new Part2(5);
        long expectedRes = 0;
        long result = p.removeByIndex(p.createArrayList(), 4);
        Assert.assertEquals(expectedRes, result);
    }

    @Test
    public void linkedListIndexTest() {
        Part2 p = new Part2(10000);
        long result = p.removeByIndex(p.createArrayList(), 4);
        Assert.assertFalse(result > 15);
    }

    @Test
    public void indexTest() {
        Part2 p = new Part2(10000);
        long resultL = p.removeByIndex(p.createLinkedList(), 4);
        long resultA = p.removeByIndex(p.createArrayList(), 4);
        Assert.assertFalse(resultA > resultL);
    }

    @Test
    public void iteratorTest() {
        Part2 p = new Part2(10000);
        long resultL = p.removeByIterator(p.createLinkedList(), 4);
        long resultA = p.removeByIterator(p.createArrayList(), 4);
        Assert.assertFalse( resultA < resultL);
    }

}