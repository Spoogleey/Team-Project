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
 * @author w20009414
 */
public class CompaniesQueries {
    private final DatabaseConnection database;
    
    public CompaniesQueries() {
        database = new DatabaseConnection();
    }
    
    // Code to add a new company
    public void addCompany(String name) {
        database.Connect("events.db");
        String sql = "INSERT INTO companies (company_name) VALUES ('"+name+"');";
        boolean pass = database.RunSQL(sql);
        if(!pass) {
            System.out.println("Failed to add a new company.");
        }
    }
    
    // Code to select companies with users logged in
    public String selectLoggedUser() {
        database.Connect("events.db");
        String name = "";
        String sql = "SELECT company_name FROM users INNER JOIN companies ON companies.company_id = users.company_id WHERE logged_in = 1;";
        ResultSet rs = database.RunSQLQuery(sql);
        try {
            while(rs.next()) {
                name = rs.getString(1);
            }
        } catch(SQLException e) {
            System.out.println("Failed to select the company name: " +e.getMessage());
        }
        return name;
    }
    
    // Code to get the company id
    public int selectCompany() {
        database.Connect("events.db");
        int id = 0;
        String sql = "SELECT company_name FROM users INNER JOIN companies ON companies.company_id = users.company_id WHERE logged_in = 1;";
        ResultSet rs = database.RunSQLQuery(sql);
        try {
            while(rs.next()) {
                id = rs.getInt(1);
            }
        } catch(SQLException e) {
            System.out.println("Failed to select the company name: " +e.getMessage());
        }
        return id;
    }
}
