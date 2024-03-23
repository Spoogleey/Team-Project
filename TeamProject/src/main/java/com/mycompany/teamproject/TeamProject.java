/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.teamproject;

import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author green
 */
public class TeamProject {

    public static void main(String[] args) throws SQLException {
        UsersQueries manager = new UsersQueries();
        manager.logoutUser();
        LoginForm a = new LoginForm();
        a.setVisible(true);
        
    }
    
}