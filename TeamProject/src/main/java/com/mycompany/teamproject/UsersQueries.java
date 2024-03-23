/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.teamproject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author green
 */
public class UsersQueries {
    private final DatabaseConnection database;
    
    public UsersQueries() {
        database = new DatabaseConnection();
    }
    
    // Code to add new user
    public void addUser(String first, String second, String username, String password, String dob, String email, String preference) {
        database.Connect("events.db");
        String sql = "INSERT INTO users (firstname, surname, username, password, dateofbirth, email, preference1, points) VALUES ('"+first+"', '"+second+"', '"+username+"', '"+password+"', '"+dob+"', '"+email+"', '"+preference+"', 0);";
        boolean pass = database.RunSQL(sql);
        if(!pass) {
            System.out.println("Failed to add a new user.");
        }
    }
    
    // Code to add company to user
    public void addCompany(int company, int user) {
        database.Connect("events.db");
        String sql = "UPDATE users SET company_id = "+company+" WHERE user_id = "+user+";";
        boolean pass = database.RunSQL(sql);
        if(!pass) {
            System.out.println("Failed to asign company to the user.");
        }
    }
    
    // Code to select the username and password of users
    public ArrayList<Users> selectUsers() {
        database.Connect("events.db");
        String sql = "SELECT username, password FROM users;";
        ResultSet rs = database.RunSQLQuery(sql);
        ArrayList<Users> results = new ArrayList<>();
        try {
            while(rs.next()) {
                Users user = new Users();
                user.setUsername(rs.getString(1));
                user.setPassword(rs.getString(2));
                results.add(user);
            }
        } catch(SQLException e) {
            System.out.println("Failed to select users: " +e.getMessage());
        }
        return results;
    }
    
    // Code for logging in a user
    public void loginUser(String user) {
        database.Connect("events.db");
        String sql = "UPDATE users SET logged_in = 1 WHERE username = '"+user+"';";
        boolean pass = database.RunSQL(sql);
        if(!pass) {
            System.out.println("Failed to login the user.");
        }
    }
    
    // Code to logout
    public void logoutUser() {
        database.Connect("events.db");
        String sql = "UPDATE users SET logged_in = 0;";
        boolean pass = database.RunSQL(sql);
        if(!pass) {
            System.out.println("Failed to log out users.");
        }
    }
}
