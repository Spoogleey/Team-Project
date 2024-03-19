/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.teamproject;

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
}
