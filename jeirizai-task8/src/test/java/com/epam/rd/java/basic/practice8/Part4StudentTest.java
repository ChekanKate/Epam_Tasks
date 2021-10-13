package com.epam.rd.java.basic.practice8;

import com.epam.rd.java.basic.practice8.db.DBManager;
import com.epam.rd.java.basic.practice8.db.entity.Team;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class Part4StudentTest {
    @Test
    public void deleteTest(){
        DBManager dbManager = DBManager.getInstance();
        dbManager.deleteTeam(Team.createTeam("t1"));
        assertTrue(true);
    }
}