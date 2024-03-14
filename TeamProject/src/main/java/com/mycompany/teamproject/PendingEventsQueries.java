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
public class PendingEventsQueries {
    private final DatabaseConnection database;
    
    public PendingEventsQueries() {
        database = new DatabaseConnection();
    }
    
    // Approve an event
    public void approveEvent(int eventID) {
        database.Connect("events.db");
        String sql = "UPDATE events SET approved = 1 WHERE event_id = '"+eventID+"';";
        boolean pass = database.RunSQL(sql);
        if(!pass) {
            System.out.println("Failed to approve this event.");
        }
    }
    
    // View all of the pending events
    public ArrayList<PendingEvents> getPendingEvents() {
        database.Connect("events.db");
        String sql = "SELECT event_name, event_desc, event_venue, location_name, music_name, event_date, event_price FROM events INNER JOIN locations ON locations.location_id = events.location_id INNER JOIN music ON music.music_id = events.music_id WHERE approved = 0;";
        ResultSet rs = database.RunSQLQuery(sql);
        ArrayList<PendingEvents> results = new ArrayList<>();
        try {
            while(rs.next()) {
                PendingEvents pending = new PendingEvents();
                pending.setName(rs.getString(1));
                pending.setDesc(rs.getString(2));
                pending.setVenue(rs.getString(3));
                pending.setLocation(rs.getString(4));
                pending.setMusic(rs.getString(5));
                pending.setDate(rs.getString(6));
                pending.setPrice(rs.getFloat(7));
                results.add(pending);
            }
        } catch(SQLException e) {
            System.out.println("Failed to select pending events: " +e.getMessage());
        }
        return results;
        }
}
