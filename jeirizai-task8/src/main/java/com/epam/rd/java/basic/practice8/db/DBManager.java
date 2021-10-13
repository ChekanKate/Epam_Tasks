package com.epam.rd.java.basic.practice8.db;
import com.epam.rd.java.basic.practice8.db.entity.Team;
import com.epam.rd.java.basic.practice8.db.entity.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.sql.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DBManager {

    private static DBManager instance;
    private static Connection connection;
    private static final String FILE = "app.properties";
    private static final Lock CONNECTION_LOCK = new ReentrantLock();

    private static final String FIND_ALL_USERS = "SELECT * FROM users";
    private static final String FIND_ALL_TEAMS = "SELECT * FROM teams";
    private static final String INSERT_USER = "INSERT INTO users VALUES (DEFAULT ,?)";
    private static final String INSERT_TEAM = "INSERT INTO teams VALUES (DEFAULT ,?)";
    private static final String DELETE_TEAM = "DELETE FROM teams WHERE name=?";
    private static final String UPDATE_TEAM = "UPDATE teams SET name=? WHERE id=?";
    private static final String GET_TEAM = "SELECT * FROM teams WHERE name=?";
    private static final String GET_USER = "SELECT * FROM users WHERE login=?";
    private static final String GET_USER_TEAMS = "SELECT t.id, t.name FROM users_teams ut\n"
            + "JOIN users u ON ut.user_id = u.id\n" + "JOIN teams t ON ut.team_id = t.id\n" + "WHERE u.id = ?";

    private static final String SET_TEAMS_FOR_USER = "INSERT INTO users_teams VALUES (?, ?)";

    private DBManager() {
        connection = getConnection();
    }

    public static DBManager getInstance() {
        if (instance == null) {
                instance = new DBManager();
        }
        return instance;
    }

    public Connection getConnection(){
        try{
            FileInputStream fileInputStream = new FileInputStream(FILE);
            ResourceBundle resourceBundle = new PropertyResourceBundle(fileInputStream);
            return DriverManager.getConnection(resourceBundle.getString("connection.url"));
        }catch (SQLException | IOException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public boolean insertUser(User user) {
        PreparedStatement ps1 = null;
        ResultSet id1 = null;
        try {
            CONNECTION_LOCK.lock();
            ps1 = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);
            ps1.setString(1, user.getLogin());
            if (ps1.executeUpdate() != 1) {
                return false;
            }
            id1 = ps1.getGeneratedKeys();
            if (id1.next()) {
                int idField = id1.getInt(1);
                user.setId(idField);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try{
                close(id1);
            }finally {
                close(ps1);
                CONNECTION_LOCK.unlock();
            }
        }
        return true;
    }

    public List<User> findAllUsers() {
        Statement ps2 = null;
        ResultSet rs2 = null;
        List<User> users = new ArrayList<>();
        try {
            CONNECTION_LOCK.lock();
            ps2 = connection.createStatement();
            rs2 = ps2.executeQuery(FIND_ALL_USERS);
            while (rs2.next()) {
                User user = new User();
                users.add(user);
                user.setId(rs2.getInt(1));
                user.setLogin(rs2.getString(2));
            }
        } catch (Exception e) {
            System.out.println("find all users:" + e.getMessage());
        } finally {
            try{
                close(rs2);
            }finally {
                close(ps2);
                CONNECTION_LOCK.unlock();
            }
        }
        return users;
    }

    public boolean insertTeam(Team team) {
        PreparedStatement ps3 = null;
        ResultSet id3 = null;
        try {
            CONNECTION_LOCK.lock();
            ps3 = connection.prepareStatement(INSERT_TEAM, Statement.RETURN_GENERATED_KEYS);
            ps3.setString(1, team.getName());
            if (ps3.executeUpdate() != 1) {
                return false;
            }
            id3 = ps3.getGeneratedKeys();
            if (id3.next()) {
                int idField = id3.getInt(1);
                team.setId(idField);
            }
        } catch (Exception e) {
            System.out.println("insert team:" + e.getMessage());
        } finally {
            try{
                close(id3);
            }finally {
                close(ps3);
                CONNECTION_LOCK.unlock();
            }
        }
        return true;
    }

    public List<Team> findAllTeams() {
        Statement ps4 = null;
        ResultSet rs4 = null;
        List<Team> teams = new ArrayList<>();
        try {
            CONNECTION_LOCK.lock();
            ps4 = connection.createStatement();
            rs4 = ps4.executeQuery(FIND_ALL_TEAMS);
            while (rs4.next()) {
                Team team = new Team();
                teams.add(team);
                team.setId(rs4.getInt(1));
                team.setName(rs4.getString(2));
            }
        } catch (Exception e) {
            System.out.println("find all users:" + e.getMessage());
        } finally {
            try{
                close(rs4);
            }finally {
                close(ps4);
                CONNECTION_LOCK.unlock();
            }
        }
        return teams;
    }

    public User getUser(String login) {
        PreparedStatement pr5 = null;
        ResultSet rs5 = null;
        User user = null;
        try{
            CONNECTION_LOCK.lock();
            pr5 = connection.prepareStatement(GET_USER);
            pr5.setString(1, login);
            rs5 = pr5.executeQuery();
            if(rs5.next()){
                user = new User();
                user.setId(rs5.getInt("id"));
                user.setLogin(rs5.getString("login"));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            try {
                close(rs5);
            }finally {
                close(pr5);
                CONNECTION_LOCK.unlock();
            }
        }
        return user;
    }

    public Team getTeam(String name) {
        PreparedStatement ps6 = null;
        ResultSet rs6 = null;
        Team team = null;
        try{
            CONNECTION_LOCK.lock();
            ps6 = connection.prepareStatement(GET_TEAM);
            ps6.setString(1, name);
            rs6 = ps6.executeQuery();
            if(rs6.next()){
                team = new Team();
                team.setId(rs6.getInt("id"));
                team.setName(rs6.getString("name"));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            try {
                close(rs6);
            }finally {
                close(ps6);
                CONNECTION_LOCK.unlock();
            }
        }
        return team;
    }

    public boolean setTeamsForUser(User user, Team... teams) {
        PreparedStatement ps7 = null;
        ResultSet rs7 = null;
        try {
            CONNECTION_LOCK.lock();
            connection.setAutoCommit(false);
            ps7 = connection.prepareStatement(SET_TEAMS_FOR_USER);
            for (Team t : teams) {
                ps7.setInt(1, user.getId());
                ps7.setInt(2, t.getId());
                ps7.addBatch();
            }
            int[] usersTeams = ps7.executeBatch();
            for (int i : usersTeams) {
                if (i != 1) {
                    return false;
                }
            }
            connection.commit();
            return true;
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("set teams");
        } finally {
            try {
                close(rs7);
            }finally {
                close(ps7);
                setAutocommit();
                CONNECTION_LOCK.unlock();
            }
        }
        return false;
    }

    public List<Team> getUserTeams(User user) {
        PreparedStatement ps8 = null;
        ResultSet rs8 = null;
        List<Team> teams = new ArrayList<>();
        try {
            CONNECTION_LOCK.lock();
            ps8 = connection.prepareStatement(GET_USER_TEAMS);
            ps8.setInt(1, user.getId());
            rs8 = ps8.executeQuery();
            while (rs8.next()) {
                Team team = new Team();
                teams.add(team);
                team.setId(rs8.getInt(1));
                team.setName(rs8.getString(2));
            }
        } catch (Exception e) {
            System.out.println("get teams:" + e.getMessage());
        } finally {
            try{
                close(rs8);
            }finally {
                close(ps8);
                CONNECTION_LOCK.unlock();
            }
        }
        return teams;
    }

    public boolean deleteTeam(Team team) {
        PreparedStatement pr9 = null;
        ResultSet id9 = null;
        try{
            CONNECTION_LOCK.lock();
            pr9 = connection.prepareStatement(DELETE_TEAM);
            pr9.setString(1, team.getName());
            if(pr9.executeUpdate() != 1){
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                close(id9);
            }finally {
                close(pr9);
                CONNECTION_LOCK.unlock();
            }
        }
        return true;
    }

    public boolean updateTeam(Team team) {
        PreparedStatement pr0 = null;
        ResultSet id0 = null;
        try{
            CONNECTION_LOCK.lock();
            pr0 = connection.prepareStatement(UPDATE_TEAM);
            pr0.setString(1, team.getName());
            pr0.setInt(2, team.getId());
            if(pr0.executeUpdate() != 1){
                return false;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            try{
                close(id0);
            }finally {
                close(pr0);
                CONNECTION_LOCK.unlock();
            }
        }
        return true;
    }

    private static void close(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void setAutocommit() {
        try {
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
