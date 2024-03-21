/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.teamproject;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author jacka
 */
public class userHomeQueries {
    private final DatabaseConnection database;
    private final String filename = "events.db";
    userHome a = new userHome();
    
    public userHomeQueries(){
        database = new DatabaseConnection();
       
    }
    
    //Gets locations for use in combobox
    public ResultSet getLocations(){
            database.Connect(filename);
            String sql = "SELECT location_name "
                    + "FROM locations ";
            ResultSet allLocations = database.RunSQLQuery(sql);
            
            return allLocations;
    }
    
    //gets music genres for use in combo box
    public ResultSet getMusicGenres(){
        database.Connect(filename);
        String sql = "SELECT music_name "
                + "FROM music";
        ResultSet allMusicGenres = database.RunSQLQuery(sql);
        
        return allMusicGenres;
    }
    
    //locationPref
    //genrePref
    //pricePref
    //minAgepref
    //maxAgePref
    
    //gets events tailored to users preferences
    public String fetchEvents(){
       ArrayList preferences = a.getFilters();
        database.Connect(filename);
        
        String locationPref = (String) preferences.get(0);
        Integer minAgePref = (Integer) preferences.get(1);
        Integer maxAgePref = (Integer) preferences.get(2);
        String genrePref = (String) preferences.get(3);
        Integer pricePref = (Integer) preferences.get(4);
        
        //Join 
        //location, music and event table
        //on location_id, music_id
        
        //Get 
        //event_name
        //event_desc
        //event_venue
        //event_date
        //event_price
        
        //Filters
        //location_name
        //music_name
        //event_price
        //min_age
        //max_age
        
        String sql = "SELECT event_name, event_desc, event_venue, event_data, event_price"
                + "FROM events"
                + "INNER JOIN locations"
                + "ON events.location_id = locations.loaction_id"
                + "INNER JOIN music"
                + "ON events.music_id = music.music_id"
                + "WHERE location_name = '"+locationPref +"'"
                + "AND music_name = '"+genrePref +"'"
                + "AND event_price <= "+pricePref
                + "AND min_age >= "+minAgePref
                + "AND max_age <= "+maxAgePref
                ;
        
        return sql;
        
    }
}
