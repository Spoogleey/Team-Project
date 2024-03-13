/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.teamproject;

/**
 *
 * @author green
 */
public class LocationQueries {
    private final DatabaseConnection database;
    
    public LocationQueries() {
        database = new DatabaseConnection();
    }
    
    // Add a new location
    public void addLocation(String location) {
        database.Connect("events.db");
        String sql = "INSERT INTO locations (location_name) VALUES ('"+location+"');";
        boolean pass = database.RunSQL(sql);
        if(!pass) {
            System.out.println("Failed to add a new location.");
        }
    }
    
    // Select all of the loactions
    
}
