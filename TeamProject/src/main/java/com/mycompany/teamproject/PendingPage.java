/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.teamproject;
import java.sql.*;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author User
 */
public class PendingPage extends javax.swing.JFrame {

     DatabaseConnection database;
     ResultSet rs = null;
    /**
     * Creates new form PendingPage
     */
    public PendingPage() {
        initComponents();
        database = new DatabaseConnection();
        UpdatePendingTable();
    }
    
    //updates the events shown in the tables for clients 
    private void UpdatePendingTable(){
        database.Connect("events.db");
        //Query for pending events
        CompaniesQueries company = new CompaniesQueries(); // Create an instance of AnalyticsManager
        int companyID = company.selectCompany();
        String sql = "SELECT * FROM events WHERE approved=0 AND company_id="+companyID+";";
        try{
            rs = database.RunSQLQuery(sql);
        
            while(rs.next()){
                String id = String.valueOf(rs.getInt("event_id"));
                String Event = rs.getString("event_name");
                
                //String array to store the data in the jtable
                
                String tbData [] = {id, Event};
                DefaultTableModel PendModel = (DefaultTableModel)PendingTable.getModel();
                
                //add the string array data into the jtable
                PendModel.addRow(tbData);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        PendingTable = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        AddEvents1 = new javax.swing.JButton();
        ApprovedEvents1 = new javax.swing.JButton();
        AnalyticsEvents = new javax.swing.JButton();
        DeniedEvents = new javax.swing.JButton();
        GoBack5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        PendingTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Event ID", "Pending"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        PendingTable.setPreferredSize(new java.awt.Dimension(700, 360));
        jScrollPane1.setViewportView(PendingTable);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        AddEvents1.setText("Add Events");
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

        AnalyticsEvents.setText("Analytics");
        AnalyticsEvents.setToolTipText("");
        AnalyticsEvents.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnalyticsEventsActionPerformed(evt);
            }
        });

        DeniedEvents.setText("Denied Events");
        DeniedEvents.setToolTipText("");
        DeniedEvents.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeniedEventsActionPerformed(evt);
            }
        });

        GoBack5.setText("Log Out");
        GoBack5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GoBack5ActionPerformed(evt);
            }
        });

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
                    .addComponent(AnalyticsEvents, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(DeniedEvents, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(AddEvents1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ApprovedEvents1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DeniedEvents)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AnalyticsEvents)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(GoBack5)
                .addContainerGap(309, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void AnalyticsEventsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AnalyticsEventsActionPerformed
        // TODO add your handling code here:
        this.dispose();
        AnalyticsScreen obj = new AnalyticsScreen();
        obj.setVisible(true);
    }//GEN-LAST:event_AnalyticsEventsActionPerformed

    private void DeniedEventsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeniedEventsActionPerformed
        // TODO add your handling code here:
        this.dispose();
        DeniedPage obj = new DeniedPage();
        obj.setVisible(true);
    }//GEN-LAST:event_DeniedEventsActionPerformed

    private void GoBack5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GoBack5ActionPerformed
        // TODO add your handling code here:
        UsersQueries manager = new UsersQueries();
        manager.logoutUser();
        this.dispose();
        LoginForm obj = new LoginForm();
        obj.setVisible(true);
    }//GEN-LAST:event_GoBack5ActionPerformed

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
            java.util.logging.Logger.getLogger(PendingPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PendingPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PendingPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PendingPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PendingPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddEvents1;
    private javax.swing.JButton AnalyticsEvents;
    private javax.swing.JButton ApprovedEvents1;
    private javax.swing.JButton DeniedEvents;
    private javax.swing.JButton GoBack5;
    private javax.swing.JTable PendingTable;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
