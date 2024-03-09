/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.teamproject;

/**
 *
 * @author green
 */
public class AddQueries {
    private final DatabaseConnection database;
    
    public AddQueries() {
        database = new DatabaseConnection();
    }
    
    // Code for adding a new music genre
    public void addMusic(String music) {
        database.Connect("TeamProject.db");
        String sql = "INSERT INTO music (music_name) VALUES ('"+music+"');";
        boolean pass = database.RunSQL(sql);
        if(!pass) {
            System.out.println("Failed to add a new music genre to the table.");
        }
    }
    
    // Code for adding a new location
    public void addLocation(String location) {
        database.Connect("TeamProject.db");
        String sql = "INSERT INTO locations (location_name) VALUES ('"+location+"');";
        boolean pass = database.RunSQL(sql);
        if(!pass) {
            System.out.println("Failed to add a new location to the table.");
        }
    }
    
    // Code for adding new event
    public void addEvent(String name, String description, String venue, int location, int music, float price) {
        database.Connect("TeamProject.db");
        String sql = "INSERT INTO events (event_name, event_desc, event_venue, location_id, music_id, event_price) VALUES ('"+name+"', '"+description+"', '"+venue+"', "+location+", "+music+", "+price+");";
        boolean pass = database.RunSQL(sql);
        if(!pass) {
            System.out.println("Failed to add a new event to the table.");
        }
    }
    
    // Code for adding new user
    public void addUser(String username, String password) {
        database.Connect("TeamProject.db");
        String sql = "INSERT INTO users (username, password) VALUES ('"+username+"', '"+password+"');";
        boolean pass = database.RunSQL(sql);
        if(!pass) {
            System.out.println("Failed to add a new user to the table.");
        }
    }
}
