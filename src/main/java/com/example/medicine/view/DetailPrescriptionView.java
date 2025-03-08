/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.example.medicine.view;

import com.example.medicine.model.Prescription;
import com.example.medicine.service.PrescriptionService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author ThinhTran
 */
public class DetailPrescriptionView extends javax.swing.JFrame {
    private final PrescriptionService prescriptionService;
    private static Integer patientId;

    public static void setPatientId(Integer patientId) {
        DetailPrescriptionView.patientId = patientId;
    }

    /**
     * Creates new form DetailPrescriptionView
     */
    public DetailPrescriptionView() {
        initComponents();
        this.prescriptionService = new PrescriptionService();


        loadData(prescriptionService.getPrescriptionByPatient(patientId));
        loadCb();

        sHour.setModel(new javax.swing.SpinnerNumberModel(0, 0, 23, 1));
        sMinute.setModel(new javax.swing.SpinnerNumberModel(0, 0, 59, 1));

        sHour.setValue(8);
        sMinute.setValue(0);

        setLocationRelativeTo(null);
    }

    private void loadCb() {
        cbMedicineName.removeAllItems();

        List<String> medicineNames = prescriptionService.getAllMedicineNames(patientId);

        for (String medicineName : medicineNames) {
            cbMedicineName.addItem(medicineName);
        }
    }


    private void loadData(List<Prescription> prescriptions) {
        if (prescriptions == null || prescriptions.isEmpty()) {
            System.out.println("No prescriptions available.");
            tbPrescriptions.setModel(new DefaultTableModel());
            return;
        }

        System.out.println("Prescriptions:");
        prescriptions.forEach(e -> System.out.println(e.getTime() + e.getMedicineName() + e.getQuantity()));

        List<String> times = prescriptions.stream()
                .filter(p -> p.getTime() != null)
                .map(p -> p.getTime().toString())
                .distinct()
                .sorted()
                .toList();

        System.out.println(times);

        List<String> medicineNames = prescriptions.stream()
                .map(Prescription::getMedicineName)
                .filter(Objects::nonNull)
                .distinct()
                .toList();

        DefaultTableModel tableModel = new DefaultTableModel();

        tableModel.addColumn("Time");

        for (String medicine : medicineNames) {
            tableModel.addColumn(medicine);
        }

        for (String time : times) {
            Object[] row = new Object[medicineNames.size() + 1];
            row[0] = time;

            for (int i = 0; i < medicineNames.size(); i++) {
                String medicineName = medicineNames.get(i);

                Prescription match = prescriptions.stream()
                        .filter(p -> p != null && p.getTime() != null && p.getTime().toString().equals(time)
                                && p.getMedicineName() != null && p.getMedicineName().equals(medicineName))
                        .findFirst()
                        .orElse(null);

                row[i + 1] = (match != null) ? match.getQuantity() : "";
            }

            tableModel.addRow(row);
        }

        if (!medicineNames.isEmpty()) {
            Object[] totalRow = new Object[medicineNames.size() + 1];
            totalRow[0] = "Total";

            for (int i = 0; i < medicineNames.size(); i++) {
                String medicineName = medicineNames.get(i);

                int totalQuantity = prescriptions.stream()
                        .filter(p -> p.getMedicineName() != null && p.getMedicineName().equals(medicineName))
                        .mapToInt(p -> p.getQuantity() != null ? p.getQuantity() : 0)
                        .sum();

                totalRow[i + 1] = totalQuantity;
            }

            tableModel.addRow(totalRow);
        }

        tbPrescriptions.setModel(tableModel);

        tableModel.addTableModelListener(e -> {
            int row = e.getFirstRow();
            int column = e.getColumn();

            if (row == tableModel.getRowCount() - 1) {
                return;
            }

            if (column > 0) {
                String medicineName = medicineNames.get(column - 1);
                String time = (String) tableModel.getValueAt(row, 0);

                Object newQuantityObj = tableModel.getValueAt(row, column);
                if (newQuantityObj != null) {
                    try {
                        int newQuantity = Integer.parseInt(newQuantityObj.toString());

                        System.out.println("Update quantity: " + newQuantity + " for " + medicineName + " at " + time);
                        prescriptionService.updateQuantity(newQuantity, patientId, medicineName, time);

                        int newTotal = 0;
                        for (int i = 0; i < tableModel.getRowCount() - 1; i++) {
                            Object cellValue = tableModel.getValueAt(i, column);
                            if (cellValue != null && !cellValue.toString().isEmpty()) {
                                try {
                                    newTotal += Integer.parseInt(cellValue.toString());
                                } catch (NumberFormatException ignored) {
                                }
                            }
                        }

                        tableModel.setValueAt(newTotal, tableModel.getRowCount() - 1, column);
                    } catch (NumberFormatException ex) {
                        System.out.println("Invalid quantity format: " + newQuantityObj);
                    }
                }
            }
        });
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
        tbPrescriptions = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbMedicineName = new javax.swing.JComboBox<>();
        btnAddMedicine = new javax.swing.JButton();
        btnAddTime = new javax.swing.JButton();
        sHour = new javax.swing.JSpinner();
        sMinute = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tbPrescriptions.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null}
                },
                new String[]{
                        "Title 1", "Title 2", "Title 3", "Title 4"
                }
        ));
        jScrollPane1.setViewportView(tbPrescriptions);

        jLabel1.setText("Medicine name:");

        jLabel2.setText("Time:");

        cbMedicineName.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));

        btnAddMedicine.setText("Add medicine");
        btnAddMedicine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddMedicineActionPerformed(evt);
            }
        });

        btnAddTime.setText("Add Time");
        btnAddTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddTimeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1062, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(cbMedicineName, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(sHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(sMinute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(173, 173, 173))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(118, 118, 118)
                                .addComponent(btnAddMedicine)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAddTime)
                                .addGap(180, 180, 180))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2)
                                        .addComponent(cbMedicineName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(sHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(sMinute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(35, 35, 35)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnAddMedicine)
                                        .addComponent(btnAddTime))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddMedicineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddMedicineActionPerformed
        Integer medicineId = Integer.parseInt(cbMedicineName.getSelectedItem().toString().split(" - ")[0]);

        prescriptionService.addMedicine(medicineId, patientId);

        loadData(prescriptionService.getPrescriptionByPatient(patientId));
        loadCb();
    }//GEN-LAST:event_btnAddMedicineActionPerformed

    private void btnAddTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddTimeActionPerformed
        String time = String.format("%02d:%02d:00", sHour.getValue(), sMinute.getValue());

        if (prescriptionService.isExistedTime(time, patientId)) {
            JOptionPane.showMessageDialog(this, "Time existed", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        prescriptionService.addTime(patientId, time);

        loadData(prescriptionService.getPrescriptionByPatient(patientId));
    }//GEN-LAST:event_btnAddTimeActionPerformed

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
            java.util.logging.Logger.getLogger(DetailPrescriptionView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DetailPrescriptionView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DetailPrescriptionView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DetailPrescriptionView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DetailPrescriptionView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddMedicine;
    private javax.swing.JButton btnAddTime;
    private javax.swing.JComboBox<String> cbMedicineName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner sHour;
    private javax.swing.JSpinner sMinute;
    private javax.swing.JTable tbPrescriptions;
    // End of variables declaration//GEN-END:variables
}
