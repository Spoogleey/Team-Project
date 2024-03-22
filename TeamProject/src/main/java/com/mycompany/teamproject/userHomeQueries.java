/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.teamproject;



import java.sql.*;
import java.util.ArrayList;
import com.mycompany.teamproject.userHome;
/**
 *
 * @author jacka
 */
public class userHomeQueries {
    private final DatabaseConnection database;
    private final String filename = "events.db";
   
    
    public userHomeQueries(){
        database = new DatabaseConnection();
        database.Connect(filename);
        
       
    }
    
    //Gets locations for use in combobox
    public ResultSet getLocations(){
            String sql = "SELECT location_name "
                    + "FROM locations ";
            ResultSet allLocations = database.RunSQLQuery(sql);
            
            return allLocations;
            
    }
    
    //gets music genres for use in combo box
    public ResultSet getMusicGenres(){
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
    public void fetchEvents(){
        
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
        
        
    }
}
