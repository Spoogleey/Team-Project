/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.teamproject;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author green
 */
public class AnalyticsQueries {
    private final DatabaseConnection database;
    
    public AnalyticsQueries() {
        database = new DatabaseConnection();
    }
    
    // Add new analytics for an event
    public void addAnalytics(int id, int tickets, float booking) {
        database.Connect("events.db");
        String sql = "INSERT INTO analytics (event_id, tickets_sold, booking_fee) VALUES ("+id+", "+tickets+", "+booking+");";
        boolean pass = database.RunSQL(sql);
        if(!pass) {
            System.out.println("Failed to add new event analytics.");
        }
    }
    
    // View the analytics for the events
    public void getAnalytics() {
        database.Connect("events.db");
        String sql = "SELECT event_name, tickets_sold, sales, booking_fee, profit FROM analytics INNER JOIN events ON events.event_id = analytics.event_id;";
        ResultSet rs = database.RunSQLQuery(sql);
        try {
            while(rs.next()) {
                String name = rs.getString(1);
                System.out.println(name+", ");
                int tickets = rs.getInt(2);
                System.out.println(tickets+", ");
                float sales = rs.getFloat(3);
                System.out.println(sales+", ");
                float booking = rs.getFloat(4);
                System.out.println(booking+", ");
                float profit = rs.getFloat(5);
                System.out.println(profit+"\n");
            }
        } catch(SQLException e) {
            System.out.println("Failed to get analytics: " +e.getMessage());
        }
    }
}
