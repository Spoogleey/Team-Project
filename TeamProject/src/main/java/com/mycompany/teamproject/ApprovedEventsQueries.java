/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.teamproject;

import java.sql.SQLException;
import java.sql.ResultSet;

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
    public void getApprovedEvents() {
        database.Connect("events.db");
        String sql = "SELECT event_name, event_desc, event_venue, location_name, music_name, event_date, event_price FROM events INNER JOIN locations ON locations.location_id = events.location_id INNER JOIN music ON music.music_id = events.music_id WHERE approved = 1;";
        ResultSet rs = database.RunSQLQuery(sql);
        try {
            while(rs.next()) {
                String name = rs.getString(1);
                System.out.print(name+", ");
                String desc = rs.getString(2);
                System.out.print(desc+", ");
                String venue = rs.getString(3);
                System.out.print(venue+", ");
                String location = rs.getString(4);
                System.out.print(location+", ");
                String music = rs.getString(5);
                System.out.print(music+", ");
                String date = rs.getString(6);
                System.out.print(date+", ");
                float price = rs.getFloat(7);
                System.out.print(price+"\n"); 
            }
            } catch(SQLException e) {
                    System.out.println("Failed to select approved events: " +e.getMessage());
                    }
        }
    }
