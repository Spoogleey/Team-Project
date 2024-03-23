/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.teamproject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * @author green
 */
public class DatabaseConnection {
    private Connection conn;
    
    public DatabaseConnection() {
        conn = null;
    }
    
    // Code to connect to the SQLite database.
    public boolean Connect(String filename)
    {
        filename = "events.db";
        try {
            String url = "jdbc:sqlite:"+filename;
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to "+filename+" has been established");
        }
        catch(SQLException e) {
            System.out.println("Failed to connect to "+filename);
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    
    // Code to run an sql query.
    public boolean RunSQL(String sql) {
        if(conn == null) {
            System.out.println("There is no database loaded. Cannot run SQL.");
            return false;
        }
        try {
            Statement sqlStatement = conn.createStatement();
            sqlStatement.execute(sql);
        }
        catch(SQLException e) {
            System.out.println("Failed to execute "+sql);
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    
    // Code to run an sql SELECT query.
    public ResultSet RunSQLQuery(String sql) {
        ResultSet results;
        if(conn == null) {
            System.out.println("There is no database loaded. Cannot run SQL");
            return null;
        }
        try {
            Statement sqlStatement = conn.createStatement();
            results = sqlStatement.executeQuery(sql);
        }
        catch(SQLException e) {
            System.out.println("Failed to execute "+sql);
            System.out.println(e.getMessage());
            return null;
        }
        return results;
    }
}
