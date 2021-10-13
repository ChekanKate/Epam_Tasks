package com.epam.rd.java.basic.practice8;

import com.epam.rd.java.basic.practice8.db.DBManager;
import com.epam.rd.java.basic.practice8.db.entity.Team;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class Part5StudentTest {
    @Test
    public void updateTest(){
        DBManager dbManager = DBManager.getInstance();
        dbManager.updateTeam(Team.createTeam("t1"));
        assertTrue(true);
    }
}