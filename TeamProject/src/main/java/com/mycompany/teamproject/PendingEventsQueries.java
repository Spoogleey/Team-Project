/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.teamproject;

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
    
}
