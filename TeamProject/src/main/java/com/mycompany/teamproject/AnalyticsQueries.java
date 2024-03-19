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
public class AnalyticsQueries {
    private final DatabaseConnection database;
    
    public AnalyticsQueries() {
        database = new DatabaseConnection();
    }
    
    // Add new analytics for an event
    public void addAnalytics(int id, int tickets, double booking, int company) {
        database.Connect("events.db");
        String sql = "INSERT INTO analytics (event_id, tickets_sold, booking_fee, company_id) VALUES ("+id+", "+tickets+", "+booking+", "+company+");";
        boolean pass = database.RunSQL(sql);
        if(!pass) {
            System.out.println("Failed to add new event analytics.");
        }
    }
    
    // View the analytics for the events
    public ArrayList<Analytics> getAnalytics() {
        database.Connect("events.db");
        String sql = "SELECT event_name, tickets_sold, sales, booking_fee, profit, company_id FROM analytics INNER JOIN events ON events.event_id = analytics.event_id INNER JOIN companies ON companies.company_id = analytics.company_id;";
        ResultSet rs = database.RunSQLQuery(sql);
        ArrayList<Analytics> results = new ArrayList<>();
        try {
            while(rs.next()) {
                Analytics analytics = new Analytics();
                analytics.setName(rs.getString(1));
                analytics.setTickets(rs.getInt(2));
                analytics.setSales(rs.getDouble(3));
                analytics.setBooking(rs.getDouble(4));
                analytics.setProfit(rs.getDouble(5));
                analytics.setCompany(rs.getString(6));
                results.add(analytics);
            }
        } catch(SQLException e) {
            System.out.println("Failed to select analytics: " +e.getMessage());
        }
        return results;
    }
}
