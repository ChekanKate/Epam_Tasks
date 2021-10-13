package com.epam.rd.java.basic.practice8;

import com.epam.rd.java.basic.practice8.db.DBManager;
import com.epam.rd.java.basic.practice8.db.entity.Team;
import com.epam.rd.java.basic.practice8.db.entity.User;
import org.junit.Assert;
import org.junit.Test;

import static com.epam.rd.java.basic.practice8.db.entity.Team.createTeam;
import static com.epam.rd.java.basic.practice8.db.entity.User.createUser;
import static org.junit.Assert.assertTrue;

public class Part3StudentTest {
    @Test
    public void testGetUser()
    {
        DBManager dbManager = DBManager.getInstance();
        User userPetrov = dbManager.getUser("petrov");
        assertTrue(true);
    }

    @Test
    public void testGetTeam()
    {
        DBManager dbManager = DBManager.getInstance();
        Team team1 = dbManager.getTeam("team1");
        assertTrue(true);
    }
}