package com.epam.rd.java.basic.practice8;

import com.epam.rd.java.basic.practice8.db.DBManager;
import com.epam.rd.java.basic.practice8.db.entity.User;
import org.junit.Assert;
import org.junit.Test;

public class Part1StudentTest {

    @Test
    public void insertUserTest()
    {
        DBManager dbManager = DBManager.getInstance();
        Assert.assertEquals(true, dbManager.insertUser(User.createUser("petrov")));
    }

}