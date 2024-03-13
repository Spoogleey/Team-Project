/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.teamproject;

/**
 *
 * @author green
 */
public class ApprovedEventsQueries {
    private final DatabaseConnection database;
    
    public ApprovedEventsQueries() {
        database = new DatabaseConnection();
    }
    
    // Add a new event into the events table
    public void addEvent(String name, String desc, String venue, int location, int music, String date, float price) {
        database.Connect("events.db");
        String sql = "INSERT INTO events (event_name, event_desc, event_venue, location_id, music_id, event_date, event_price, approved) VALUES ('"+name+"', '"+desc+"', '"+venue+"', "+location+", "+music+", '"+date+"', "+price+", 0);";
        boolean pass = database.RunSQL(sql);
        if(!pass) {
            System.out.println("Failed to add an event to the table.");
        }
    }
    
    // View all of the approved events
    
}
