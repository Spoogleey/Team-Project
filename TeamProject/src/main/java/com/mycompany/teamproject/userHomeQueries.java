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
    
    public userHomeQueries(){
        database = new DatabaseConnection();
    }
    
    
    public ResultSet getLocations(){
            database.Connect(filename);
            String sql = "SELECT location_name "
                    + "FROM locations ";
            ResultSet allLocations = database.RunSQLQuery(sql);
            
            return allLocations;
    }
    
    public ResultSet getMusicGenres(){
        database.Connect(filename);
        String sql = "SELECT music_name "
                + "FROM music";
        ResultSet allMusicGenres = database.RunSQLQuery(sql);
        
        return allMusicGenres;
    }
}
