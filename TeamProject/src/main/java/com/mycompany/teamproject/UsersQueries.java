/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.teamproject;

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
    public void addUser(String first, String second, String username, String password, String dob) {
        database.Connect("events.db");
        String sql = "INSERT INTO users (firstname, surname, username, password, dateofbirth) VALUES ('"+first+"', '"+second+"', '"+username+"', '"+password+"', '"+dob+"');";
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
}
