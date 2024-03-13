/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.teamproject;

/**
 *
 * @author green
 */
public class MusicQueries {
    private final DatabaseConnection database;
    
    public MusicQueries() {
        database = new DatabaseConnection();
    }
    
    // Add a new music genre
    public void addMusic(String music) {
        database.Connect("events.db");
        String sql = "INSERT INTO music (music_name) VALUEA ('"+music+"');";
        boolean pass = database.RunSQL(sql);
        if(!pass) {
            System.out.println("Failed to add a new music genre.");
        }
    }
    
    // Select all of the music genres
    
}
