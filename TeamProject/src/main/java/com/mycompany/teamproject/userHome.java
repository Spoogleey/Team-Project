/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.teamproject;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
        
/**
 *
 * @author jacka
 */
public class userHome extends javax.swing.JFrame {
    //intitalise variables
    private final DatabaseConnection database;
    private final String filename = "events.db";
    private final userHomeQueries query = new userHomeQueries();
    private CachedRowSet resultsCache;
    private int rowNum = 1;
    

    /**
     * Creates new form userHome
     */
    public userHome() throws SQLException {
        //initialise db connection and methods
        database = new DatabaseConnection();
        database.Connect(filename);
        initComponents();
        locationComboBox();
        genreComboBox();
        
        //runs previousRow on button press
        previousButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    previousRow();
                } catch (SQLException ex) {
                    Logger.getLogger(userHome.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        //runs nextRow() on button press
        nextButton.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            try {
                nextRow();
            } catch (SQLException ex) {
                Logger.getLogger(userHome.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    });
        
        //gets the sql query and populate resultsCache
        String sql = getFilters(); 
        try{
            resultsCache = RowSetProvider.newFactory().createCachedRowSet();
            ResultSet results = database.RunSQLQuery(sql);

            if (results != null){
                resultsCache.populate(results);
                updateCache();
                
            }else{
                System.out.println("No results were found");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }  
        
        
    }
    
    //When the user clicks the back button, if the rowNum is bigger than 1 sets labels to the new row
    private void previousRow() throws SQLException{
        if(rowNum > 1){
            rowNum--;
            setLabels();
        }
        
        System.out.println("Click next for more events");
    }
    
    
    //When the user clicks the next button, change labels to the next row of the cache
    private void nextRow() throws SQLException{
        
        rowNum++;
        if(resultsCache.absolute(rowNum)){
            setLabels();
        }
        //if the next row is empty decrement the rowNum and output no more events
        else{
            rowNum--;
            System.out.println("No more events found");
        }
        
    }
    
    
    //Updates the resultsCache and sets the labels
    private void updateCache() throws SQLException{
        //gets the sql query, clears the cache and sets the rowNum back to 1
        String sql = getFilters();
        resultsCache.close();
        rowNum = 1;
        
        //tries to run query catches Exception e
        try{
            ResultSet results = database.RunSQLQuery(sql);
            
            //only populates the result cache if the query has returned a list with values 
            if (results != null){
                
                resultsCache.populate(results);
            //if the results list is empty output no results
            }else{
                System.out.println("No results found");
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
        //sets labels
        setLabels();
    }
    
    //Setting the text of the location combobox
    private void locationComboBox(){
        ResultSet locations = query.getLocations();
        try {
            while(locations.next()){
                String location = locations.getString(1);
                locationCombo.addItem(location);
            }
        } catch (SQLException ex) {
            Logger.getLogger(userHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Setting the text of the genre combobox
    private void genreComboBox(){
        ResultSet genres = query.getMusicGenres();
        try{
            while(genres.next()){
                String genre = genres.getString(1);
                genreCombo.addItem(genre);
            }
        }catch (SQLException ex){
            Logger.getLogger(userHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    //Getting the users filters from the comboboxes
    public String getFilters(){

        String locationPref;
        Integer minAgePref;
        Integer maxAgePref;
        String genrePref;
        Integer pricePref = null;

        
        //Location
       locationPref = (locationCombo.getSelectedItem()).toString();
       
       //Age-Range
       String ageRangePref = (ageRangeCombo.getSelectedItem()).toString();
       if (ageRangePref.equals("Any") ){
           minAgePref = 0;
           maxAgePref = 99;
       }else{
           minAgePref = Integer.parseInt(ageRangePref.substring(0,ageRangePref.indexOf("-")));
           maxAgePref = Integer.parseInt(ageRangePref.substring(ageRangePref.indexOf("-")+1));
       }
       
       //Genre
       genrePref = (genreCombo.getSelectedItem()).toString();
       
       //Price
       String pricePrefStr = (priceRangeCombo.getSelectedItem()).toString();
        switch (pricePrefStr) {
            case "£" -> pricePref = 5;
            case "££" -> pricePref = 10;
            case "£££" -> pricePref = 20;
            case "Any" -> pricePref = 100;
            default -> {
            }
        }
        
        
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
        
        
        
        //sql query to run
       String sql = "SELECT event_name, event_desc, event_venue, event_date, event_price "
                + "FROM events "
                + "INNER JOIN locations "
                + "ON events.location_id = locations.location_id "
                + "INNER JOIN music "
                + "ON events.music_id = music.music_id "
                + "WHERE location_name = '"+locationPref +"' "
                + "AND music_name = '"+genrePref +"' "
                + "AND event_price <= "+pricePref
                + " AND min_age >= "+minAgePref
                + " AND max_age <= "+maxAgePref+ " ;"
                ;
       
       
       //for testing purposes we output the string to check if the sql query is correct
        System.out.println(sql);
       return sql;
       
    }
    
    private void setLabels() throws SQLException{ 
        
        //Generate Random Number for distance
        Random distanceNum = new Random();
        int randomNumber = distanceNum.nextInt(10) + 1;
       
            //if there is an available event sets labels to the event info
            if(resultsCache.absolute(rowNum)){
                String nameOfEvent = resultsCache.getString("event_name");
                String eventDesc = resultsCache.getString("event_desc");
                String eventVenue = resultsCache.getString("event_venue");
                String eventDate = resultsCache.getString("event_date");
                Float eventPrice = resultsCache.getFloat("event_price");
                resultsCache.beforeFirst();



                eventName.setText(nameOfEvent);
                description.setText(eventDesc);
                venue.setText("Venue: "+eventVenue);
                date.setText("Date: "+eventDate);
                price.setText("Price: £"+eventPrice.toString());
                distance.setText("Distance: "+String.valueOf(randomNumber)+ " miles");
            //If there are no events then the labels will be changed to tell user 
            }else{
                System.out.println("Row not found");
                eventName.setText("No events found ");
                description.setText(" ");
                venue.setText(" ");
                date.setText(" ");
                price.setText(" ");
                descriptionTitle.setText(" ");
                distance.setText(" ");
            }
            
        }
        
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        genreCombo = new javax.swing.JComboBox<>();
        genreLabel = new javax.swing.JLabel();
        priceRangeCombo = new javax.swing.JComboBox<>();
        priceRangeLabel = new javax.swing.JLabel();
        ageRangeCombo = new javax.swing.JComboBox<>();
        ageRangeLabel = new javax.swing.JLabel();
        locationCombo = new javax.swing.JComboBox<>();
        locationLabel = new javax.swing.JLabel();
        confirmFilterButton = new javax.swing.JButton();
        showcasePanel = new javax.swing.JPanel();
        previousButton = new javax.swing.JButton();
        nextButton = new javax.swing.JButton();
        eventName = new javax.swing.JLabel();
        distance = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        price = new javax.swing.JLabel();
        venue = new javax.swing.JLabel();
        descriptionPanel = new javax.swing.JPanel();
        descriptionTitle = new javax.swing.JLabel();
        description = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        genreCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genreComboActionPerformed(evt);
            }
        });

        genreLabel.setText("Genre:");

        priceRangeCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "£", "££", "£££", "Any", " " }));

        priceRangeLabel.setText("Price Range:");

        ageRangeCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Any", "18-30", "25-40", "30-50" }));

        ageRangeLabel.setText("Age Range:");

        locationLabel.setText("Location:");

        confirmFilterButton.setText("Filter");
        confirmFilterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmFilterButtonActionPerformed(evt);
            }
        });

        showcasePanel.setBackground(new java.awt.Color(255, 255, 255));

        previousButton.setText("Back");
        previousButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousButtonActionPerformed(evt);
            }
        });

        nextButton.setText("Next");
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });

        eventName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        eventName.setText("Event Name");

        distance.setText("Distance:");

        date.setText("Date:");

        price.setText("Price:");

        venue.setText("Venue:");

        descriptionPanel.setBackground(new java.awt.Color(204, 204, 204));

        descriptionTitle.setText("Description:");

        description.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        description.setText("jLabel7");

        javax.swing.GroupLayout descriptionPanelLayout = new javax.swing.GroupLayout(descriptionPanel);
        descriptionPanel.setLayout(descriptionPanelLayout);
        descriptionPanelLayout.setHorizontalGroup(
            descriptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, descriptionPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(descriptionTitle)
                .addGap(99, 99, 99))
            .addGroup(descriptionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(description, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        descriptionPanelLayout.setVerticalGroup(
            descriptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(descriptionPanelLayout.createSequentialGroup()
                .addComponent(descriptionTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(description, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout showcasePanelLayout = new javax.swing.GroupLayout(showcasePanel);
        showcasePanel.setLayout(showcasePanelLayout);
        showcasePanelLayout.setHorizontalGroup(
            showcasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(showcasePanelLayout.createSequentialGroup()
                .addGroup(showcasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(showcasePanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(previousButton, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(nextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(showcasePanelLayout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(eventName, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 60, Short.MAX_VALUE))
                    .addGroup(showcasePanelLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(showcasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(price, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(venue, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(showcasePanelLayout.createSequentialGroup()
                                .addComponent(distance, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(67, 67, 67)))
                        .addGap(33, 33, 33)
                        .addComponent(descriptionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(12, 12, 12)))
                .addContainerGap())
        );
        showcasePanelLayout.setVerticalGroup(
            showcasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(showcasePanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(eventName)
                .addGap(14, 14, 14)
                .addGroup(showcasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(showcasePanelLayout.createSequentialGroup()
                        .addComponent(distance)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(venue)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(date)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(price))
                    .addComponent(descriptionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addGroup(showcasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(previousButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(240, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(confirmFilterButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(showcasePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(locationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(locationCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ageRangeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ageRangeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(priceRangeLabel)
                        .addGap(5, 5, 5)
                        .addComponent(priceRangeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(genreLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(genreCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(19, 19, 19))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(genreCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(genreLabel)
                    .addComponent(priceRangeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(priceRangeLabel)
                    .addComponent(ageRangeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ageRangeLabel)
                    .addComponent(locationCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(locationLabel)
                    .addComponent(confirmFilterButton))
                .addGap(18, 18, 18)
                .addComponent(showcasePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(491, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void genreComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genreComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_genreComboActionPerformed

    private void confirmFilterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmFilterButtonActionPerformed
        try {
            // TODO add your handling code here:
            updateCache();
            
        } catch (SQLException ex) {
            Logger.getLogger(userHome.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_confirmFilterButtonActionPerformed

    private void previousButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousButtonActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_previousButtonActionPerformed

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nextButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(userHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(userHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(userHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(userHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new userHome().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(userHome.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ageRangeCombo;
    private javax.swing.JLabel ageRangeLabel;
    private javax.swing.JButton confirmFilterButton;
    private javax.swing.JLabel date;
    private javax.swing.JLabel description;
    private javax.swing.JPanel descriptionPanel;
    private javax.swing.JLabel descriptionTitle;
    private javax.swing.JLabel distance;
    private javax.swing.JLabel eventName;
    private javax.swing.JComboBox<String> genreCombo;
    private javax.swing.JLabel genreLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox<String> locationCombo;
    private javax.swing.JLabel locationLabel;
    private javax.swing.JButton nextButton;
    private javax.swing.JButton previousButton;
    private javax.swing.JLabel price;
    private javax.swing.JComboBox<String> priceRangeCombo;
    private javax.swing.JLabel priceRangeLabel;
    private javax.swing.JPanel showcasePanel;
    private javax.swing.JLabel venue;
    // End of variables declaration//GEN-END:variables
}
