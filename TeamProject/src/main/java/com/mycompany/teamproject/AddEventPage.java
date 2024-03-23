/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.teamproject;
import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author User
 */
public class AddEventPage extends javax.swing.JFrame {

    private final DatabaseConnection database;
    ResultSet rs = null;
    /**
     * Creates new form AddEventPage
     */
    public AddEventPage() {
        initComponents();
        database = new DatabaseConnection();
        updateGenreCombo();
        updatelocationCombo();
        updateCompCombo();
        
    }
    
    // call classes from other pages to allow access to adding data functions
    
    private final ApprovedEventsQueries InsertEvent = new ApprovedEventsQueries();
    private final LocationQueries InsertLocation = new LocationQueries();
    private final MusicQueries InsertMusic = new MusicQueries();
    private final CompaniesQueries InsertComp = new CompaniesQueries();
    
    String compInput = "";
    String musicInput = "";
    String locationInput = "";
    //Add Events into the SQLite Table
    
    public void AddingEvent(){
        
        
        InsertEvent.addEvent(nameText.getText(), descText.getText(), venueText.getText(), LocationCombo.getSelectedIndex() + 1, 
                musicText.getSelectedIndex() + 1, dateText.getText(), Double.parseDouble(priceText.getText()),
                Integer.parseInt(minAge.getText()), Integer.parseInt(maxAge.getText()), companyText.getSelectedIndex() + 1 );
        
        JOptionPane.showMessageDialog(null, "Event added");
       
    }
    
    //Add Companies into the SQLite Table
    
    public void AddingComp(){
        InsertComp.addCompany(compInput);
    }
    
    //Add Genre into the SQLite Table
    
    public void AddingMusic(){
        InsertMusic.addMusic(musicInput);
    }
    
    //Add Location into the SQLite Table
    
    public void AddingLocation(){
        InsertLocation.addLocation(locationInput);
    }
    
    public static boolean ValidateInput(String input){
        return input.length()>=3;
    }
    
    
    //update combo box for database details
    
    private void updateGenreCombo(){
        database.Connect("events.db");
        String sql = "SELECT * FROM music";
        try{
            rs = database.RunSQLQuery(sql);
            while(rs.next()){
                musicText.addItem(rs.getString("music_name"));
            }
        } catch (Exception e){  
        }
        
    }

    private void updatelocationCombo(){
        database.Connect("events.db");
        String sql = "SELECT * FROM locations";
        try{
            rs = database.RunSQLQuery(sql);
            while(rs.next()){
                LocationCombo.addItem(rs.getString("location_name"));
            }
        } catch (Exception e){  
        }
        
    }
    
    private void updateCompCombo(){
        database.Connect("events.db");
        String sql = "SELECT * FROM companies";
        try{
            rs = database.RunSQLQuery(sql);
            while(rs.next()){
                companyText.addItem(rs.getString("company_name"));
            }
        } catch (Exception e){  
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

        AddEventPage = new javax.swing.JPanel();
        nameText = new javax.swing.JTextField();
        descText = new javax.swing.JTextField();
        venueText = new javax.swing.JTextField();
        priceText = new javax.swing.JTextField();
        musicText = new javax.swing.JComboBox<>();
        dateText = new javax.swing.JTextField();
        addEventButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        minAge = new javax.swing.JTextField();
        EventLabel = new javax.swing.JLabel();
        DescLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        LocationCombo = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        maxAge = new javax.swing.JTextField();
        newLocalButton = new javax.swing.JButton();
        newMusicButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        AddEvents1 = new javax.swing.JButton();
        ApprovedEvents1 = new javax.swing.JButton();
        DeniedEvents1 = new javax.swing.JButton();
        PendingEvents1 = new javax.swing.JButton();
        GoBack5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("AddEvent");

        AddEventPage.setPreferredSize(new java.awt.Dimension(800, 460));

        nameText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameTextActionPerformed(evt);
            }
        });

        descText.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        descText.setMinimumSize(new java.awt.Dimension(160, 22));
        descText.setPreferredSize(new java.awt.Dimension(160, 22));

        venueText.setMaximumSize(new java.awt.Dimension(160, 22));
        venueText.setMinimumSize(new java.awt.Dimension(160, 22));
        venueText.setPreferredSize(new java.awt.Dimension(160, 22));

        priceText.setMinimumSize(new java.awt.Dimension(160, 22));
        priceText.setPreferredSize(new java.awt.Dimension(160, 22));
        priceText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                priceTextActionPerformed(evt);
            }
        });

        musicText.setMinimumSize(new java.awt.Dimension(160, 22));
        musicText.setPreferredSize(new java.awt.Dimension(160, 22));
        musicText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                musicTextActionPerformed(evt);
            }
        });

        dateText.setMinimumSize(new java.awt.Dimension(160, 22));
        dateText.setPreferredSize(new java.awt.Dimension(160, 22));

        addEventButton.setText("Add Event");
        addEventButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addEventButtonMouseClicked(evt);
            }
        });
        addEventButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addEventButtonActionPerformed(evt);
            }
        });

        clearButton.setText("Clear");
        clearButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clearButtonMouseClicked(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelButtonMouseClicked(evt);
            }
        });
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        minAge.setMinimumSize(new java.awt.Dimension(160, 22));
        minAge.setPreferredSize(new java.awt.Dimension(160, 22));
        minAge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minAgeActionPerformed(evt);
            }
        });

        EventLabel.setText("Event Name:");

        DescLabel.setText("Event Description:");

        jLabel3.setText("Event Venue:");

        jLabel4.setText("Location of venue:");

        jLabel5.setText("Genre:");

        jLabel6.setText("Date of Event:");

        jLabel7.setText("Price:");

        jLabel8.setText("Minimum Age:");

        LocationCombo.setPreferredSize(new java.awt.Dimension(160, 22));

        jLabel9.setText("Maximum Age:");

        maxAge.setMinimumSize(new java.awt.Dimension(160, 22));
        maxAge.setPreferredSize(new java.awt.Dimension(160, 22));
        maxAge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maxAgeActionPerformed(evt);
            }
        });

        newLocalButton.setText("Add New Location");
        newLocalButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                newLocalButtonMouseClicked(evt);
            }
        });
        newLocalButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newLocalButtonActionPerformed(evt);
            }
        });

        newMusicButton.setText("Add New Genre");
        newMusicButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                newMusicButtonMouseClicked(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        AddEvents1.setText("Denied Events");
        AddEvents1.setToolTipText("");
        AddEvents1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddEvents1ActionPerformed(evt);
            }
        });

        ApprovedEvents1.setText("Approved Events");
        ApprovedEvents1.setToolTipText("");
        ApprovedEvents1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ApprovedEvents1ActionPerformed(evt);
            }
        });

        DeniedEvents1.setText("Analytics");
        DeniedEvents1.setToolTipText("");
        DeniedEvents1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeniedEvents1ActionPerformed(evt);
            }
        });

        PendingEvents1.setText("Pending Events");
        PendingEvents1.setToolTipText("");
        PendingEvents1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PendingEvents1ActionPerformed(evt);
            }
        });

        GoBack5.setText("LogOut");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(GoBack5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(AddEvents1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ApprovedEvents1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(DeniedEvents1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PendingEvents1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(ApprovedEvents1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PendingEvents1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AddEvents1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DeniedEvents1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(GoBack5)
                .addContainerGap(211, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout AddEventPageLayout = new javax.swing.GroupLayout(AddEventPage);
        AddEventPage.setLayout(AddEventPageLayout);
        AddEventPageLayout.setHorizontalGroup(
            AddEventPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddEventPageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 206, Short.MAX_VALUE)
                .addGroup(AddEventPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AddEventPageLayout.createSequentialGroup()
                        .addGroup(AddEventPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(AddEventPageLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(maxAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(AddEventPageLayout.createSequentialGroup()
                                .addGroup(AddEventPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(DescLabel)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel3)
                                    .addComponent(EventLabel))
                                .addGap(2, 2, 2)
                                .addGroup(AddEventPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(nameText)
                                    .addComponent(musicText, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(priceText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(dateText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(venueText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(descText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(LocationCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(minAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(AddEventPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(newMusicButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(newLocalButton, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(13, 13, 13))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AddEventPageLayout.createSequentialGroup()
                        .addComponent(addEventButton)
                        .addGap(18, 18, 18)
                        .addComponent(clearButton)
                        .addGap(26, 26, 26)
                        .addComponent(cancelButton)
                        .addContainerGap())))
        );
        AddEventPageLayout.setVerticalGroup(
            AddEventPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddEventPageLayout.createSequentialGroup()
                .addGroup(AddEventPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(AddEventPageLayout.createSequentialGroup()
                        .addComponent(newLocalButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(newMusicButton)
                        .addGap(283, 283, 283))
                    .addGroup(AddEventPageLayout.createSequentialGroup()
                        .addGroup(AddEventPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nameText, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EventLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(AddEventPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(descText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DescLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(AddEventPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(venueText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(AddEventPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(LocationCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(AddEventPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(musicText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(AddEventPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dateText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(AddEventPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(priceText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(AddEventPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(minAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(AddEventPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(maxAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(36, 36, 36)
                        .addGroup(AddEventPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addEventButton)
                            .addComponent(clearButton)
                            .addComponent(cancelButton)))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(AddEventPage, javax.swing.GroupLayout.DEFAULT_SIZE, 794, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(AddEventPage, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void priceTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priceTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_priceTextActionPerformed

    private void musicTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_musicTextActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_musicTextActionPerformed

    private void addEventButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addEventButtonMouseClicked
        // TODO add your handling code here:
        AddingEvent();
    }//GEN-LAST:event_addEventButtonMouseClicked

    private void clearButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearButtonMouseClicked
        // TODO add your handling code here:
        //Sets values to null in the text boxes
        nameText.setText("");
        descText.setText("");
        dateText.setText("");
        LocationCombo.setSelectedItem(null);
        musicText.setSelectedItem(null);
        priceText.setText("");
        venueText.setText("");
        minAge.setText("");
        minAge.setText("");
        companyText.setSelectedItem(null);
    }//GEN-LAST:event_clearButtonMouseClicked

    private void addEventButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addEventButtonActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_addEventButtonActionPerformed

    private void nameTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameTextActionPerformed

    private void cancelButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelButtonMouseClicked
        // TODO add your handling code here:
        
        this.dispose();
        PendingPage obj = new PendingPage();
        obj.setVisible(true);
        
        
   
    }//GEN-LAST:event_cancelButtonMouseClicked

    private void minAgeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minAgeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_minAgeActionPerformed

    private void maxAgeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maxAgeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_maxAgeActionPerformed

    private void newLocalButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newLocalButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newLocalButtonActionPerformed

    private void newMusicButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_newMusicButtonMouseClicked
        // TODO add your handling code here:
        musicInput = JOptionPane.showInputDialog("Music Genre:");
        if(musicInput != null && ValidateInput(musicInput)){
            JOptionPane.showMessageDialog(null, "Valid Genre");
            AddingMusic();
            AddEventPage.setVisible(false);
            AddEventPage.setVisible(true);
            
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Invalid Genre");
        }
    }//GEN-LAST:event_newMusicButtonMouseClicked

    private void newLocalButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_newLocalButtonMouseClicked
        // TODO add your handling code here:
        locationInput = JOptionPane.showInputDialog("Location(City):");
        if(locationInput != null && ValidateInput(locationInput)){
            
            JOptionPane.showMessageDialog(null, "Valid Location.");
            AddingLocation();
            AddEventPage.setVisible(false);
            AddEventPage.setVisible(true);
            
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Invalid Location");
        }
    }//GEN-LAST:event_newLocalButtonMouseClicked

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void AddEvents1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddEvents1ActionPerformed

        this.dispose();
        AddEventPage obj = new AddEventPage();
        obj.setVisible(true);
    }//GEN-LAST:event_AddEvents1ActionPerformed

    private void ApprovedEvents1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ApprovedEvents1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        ApprovedPage obj = new ApprovedPage();
        obj.setVisible(true);
    }//GEN-LAST:event_ApprovedEvents1ActionPerformed

    private void DeniedEvents1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeniedEvents1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        DeniedPage obj = new DeniedPage();
        obj.setVisible(true);
    }//GEN-LAST:event_DeniedEvents1ActionPerformed

    private void PendingEvents1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PendingEvents1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        PendingPage obj = new PendingPage();
        obj.setVisible(true);
    }//GEN-LAST:event_PendingEvents1ActionPerformed

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
            java.util.logging.Logger.getLogger(AddEventPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddEventPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddEventPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddEventPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddEventPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AddEventPage;
    private javax.swing.JButton AddEvents1;
    private javax.swing.JButton ApprovedEvents1;
    private javax.swing.JButton DeniedEvents1;
    private javax.swing.JLabel DescLabel;
    private javax.swing.JLabel EventLabel;
    private javax.swing.JButton GoBack5;
    private javax.swing.JComboBox<String> LocationCombo;
    private javax.swing.JButton PendingEvents1;
    private javax.swing.JButton addEventButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton clearButton;
    private javax.swing.JTextField dateText;
    private javax.swing.JTextField descText;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField maxAge;
    private javax.swing.JTextField minAge;
    private javax.swing.JComboBox<String> musicText;
    private javax.swing.JTextField nameText;
    private javax.swing.JButton newLocalButton;
    private javax.swing.JButton newMusicButton;
    private javax.swing.JTextField priceText;
    private javax.swing.JTextField venueText;
    // End of variables declaration//GEN-END:variables

}
