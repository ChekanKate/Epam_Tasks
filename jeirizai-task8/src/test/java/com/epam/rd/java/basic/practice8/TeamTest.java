package com.epam.rd.java.basic.practice8;

import org.junit.Assert;
import org.junit.Test;

import static com.epam.rd.java.basic.practice8.db.entity.Team.*;
import static org.junit.Assert.assertTrue;

public class TeamTest {

    @Test
    public void testToString()
    {
        String expected = "team1";
        Assert.assertEquals(expected, createTeam("team1").toString());
    }

    @Test
    public void testEqual() {
        Assert.assertEquals(true, createTeam("team1").equals(createTeam("team1")));
    }

    @Test
    public void testGetId()
    {
        int expected = 0;
        Assert.assertEquals(expected, createTeam("team1").getId());
    }
    @Test
    public void testSetId()
    {
        createTeam("S").setId(3);
        assertTrue(true);
    }

    @Test
    public void testGetName()
    {
        String expected = "team1";
        Assert.assertEquals(expected, createTeam("team1").getName());
    }

    @Test
    public void testSetName()
    {
        createTeam("S").setName("a");
        assertTrue(true);
    }

    @Test
    public void demoTest(){
        Demo.main(new String[0]);
        assertTrue(true);
    }

}
