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
public class DeniedEventsQueries {
    private final DatabaseConnection database;
    
    public DeniedEventsQueries() {
        database = new DatabaseConnection();
    }
    
    // Deny an event
    public void denyEvent(String name, String comment) {
        database.Connect("events.db");
        String sql = "INSERT INTO denied_events (event_name, comment) VALUES ('"+name+"', '"+comment+"');";
        boolean pass = database.RunSQL(sql);
        if(!pass) {
            System.out.println("Failed to deny this event.");
        }
    }
    
    // Delete an event
    public void deleteEvent(int eventID) {
        database.Connect("events.db");
        String sql = "DELETE FROM events WHERE event_id = "+eventID+";";
        boolean pass = database.RunSQL(sql);
        if(!pass) {
            System.out.println("Failed to delete this event.");
        }
    }
    
    // View all of the denied events
    public ArrayList<DeniedEvents> getDeniedEvents() {
        database.Connect("events.db");
        String sql = "SELECT event_name, comments FROM denied_events;";
        ResultSet rs = database.RunSQLQuery(sql);
        ArrayList<DeniedEvents> results = new ArrayList<>();
        try {
            while(rs.next()) {
                DeniedEvents denied = new DeniedEvents();
                denied.setName(rs.getString(1));
                denied.setComment(rs.getString(2));
                results.add(denied);
            }
        } catch(SQLException e) {
            System.out.println("Failed to select denied events: " +e.getMessage());
        }
        return results;
    }
}
