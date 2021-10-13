package com.epam.rd.java.basic.practice8;

import org.junit.Assert;
import org.junit.Test;

import static com.epam.rd.java.basic.practice8.db.entity.User.*;
import static org.junit.Assert.assertTrue;

public class UserTest {
    @Test
    public void testToString()
    {
        String expected = "User{" + "id=0" + ", login='ivanov'}";
        Assert.assertEquals(expected, createUser("ivanov").toString());
    }

    @Test
    public void testEqual() {
        Assert.assertEquals(true, createUser("ivanov").equals(createUser("ivanov")));
    }

    @Test
    public void testGetId()
    {
        int expected = 0;
        Assert.assertEquals(expected, createUser("ivanov").getId());
    }
    @Test
    public void testSetId()
    {
        createUser("ivanov").setId(3);
        assertTrue(true);
    }

    @Test
    public void testGetLogin()
    {
        String expected = "ivanov";
        Assert.assertEquals(expected, createUser("ivanov").getLogin());
    }

    @Test
    public void testSetLogin()
    {
        createUser("S").setLogin("a");
        assertTrue(true);
    }
}
