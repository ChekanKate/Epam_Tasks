package com.epam.rd.java.basic.practice6.part1;

import org.junit.Assert;
import org.junit.Test;

import java.io.StringBufferInputStream;

public class WordContainerTest {
    @Test
    public void part1Test() {
        Part1.wordCounter(new StringBufferInputStream("asd\n" + "stop"), System.out);
        StringBuilder expectedRes = new StringBuilder("asd : 1\n");
        Assert.assertEquals(expectedRes, Part1.res);
    }

}
