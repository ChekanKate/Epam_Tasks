package com.epam.rd.java.basic.practice8;

import com.epam.rd.java.basic.practice8.db.DBManager;
import com.epam.rd.java.basic.practice8.db.entity.Team;
import org.junit.Assert;
import org.junit.Test;

public class Part2StudentTest {

    @Test
    public void insertTeamTest()
    {
        DBManager dbManager = DBManager.getInstance();
        Assert.assertEquals(true, dbManager.insertTeam(Team.createTeam("team1")));
    }
}