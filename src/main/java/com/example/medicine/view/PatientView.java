/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.example.medicine.view;

import com.example.medicine.model.Patient;
import com.example.medicine.service.PatientService;
import com.example.medicine.service.PrescriptionService;
import com.example.medicine.util.TableHelper;
import com.example.medicine.util.Validator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * @author ThinhTran
 */
public class PatientView extends javax.swing.JFrame {
    private final PatientService patientService;

    /**
     * Creates new form PatientView
     */
    public PatientView() {
        this.patientService = new PatientService();

        initComponents();

        loadData(patientService.getAllPatients());
        loadCb();

        setLocationRelativeTo(null);

        txtId.setEditable(false);

        tbPatients.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        TableHelper.setTableReadOnly(tbPatients);
    }

    private void loadCb() {
        cbGender.removeAllItems();
        cbGender.setModel(new DefaultComboBoxModel<>(new String[]{"Male", "Female", "Other"}));
    }

    private void loadData(List<Patient> patients) {
        DefaultTableModel model = (DefaultTableModel) tbPatients.getModel();

        model.setRowCount(0);

        patients.forEach(patient -> {
            model.addRow(new Object[]{
                    patient.getId(),
                    patient.getName(),
                    patient.getAge(),
                    patient.getGender(),
                    patient.getAddress(),
                    patient.getPhone(),
                    patient.getHealthCondition()
            });
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

        txtAge = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        btnClear = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPatients = new javax.swing.JTable();
        txtId = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        txtAddress = new javax.swing.JTextField();
        cbGender = new javax.swing.JComboBox<>();
        btnPrescription = new javax.swing.JButton();
        btnMedication = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtKeyword = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtHealthCodition = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel2.setText("Name:");

        jLabel4.setText("Age:");

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        jLabel6.setText("Address:");

        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        jLabel7.setText("Gender:");

        jLabel8.setText("Phone:");

        tbPatients.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Age", "Gender", "Address", "Phone", "Health Condition"
            }
        ));
        tbPatients.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPatientsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbPatients);

        jLabel1.setText("ID:");

        cbGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnPrescription.setText("Prescription");
        btnPrescription.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrescriptionActionPerformed(evt);
            }
        });

        btnMedication.setText("Medication managemnet");
        btnMedication.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMedicationActionPerformed(evt);
            }
        });

        jLabel3.setText("Search:");

        txtKeyword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtKeywordKeyTyped(evt);
            }
        });

        jLabel5.setText("Health condition:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(117, 117, 117)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(txtKeyword, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnPrescription, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(btnMedication)
                .addGap(26, 26, 26))
            .addGroup(layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAge, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(140, 140, 140)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPhone)
                            .addComponent(txtAddress)
                            .addComponent(cbGender, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txtHealthCodition, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtKeyword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(cbGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtHealthCodition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnDelete)
                    .addComponent(btnClear)
                    .addComponent(btnPrescription)
                    .addComponent(btnMedication))
                .addGap(32, 32, 32)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private Patient getPatientFromForm() {
        String id = txtId.getText();
        String name = txtName.getText();
        String age = txtAge.getText();
        String gender = cbGender.getSelectedItem() == null ? "" : cbGender.getSelectedItem().toString();
        String address = txtAddress.getText();
        String phone = txtPhone.getText();
        String healthCondition = txtHealthCodition.getText();

        if (Validator.isNullOrEmpty(name, age, gender, address, phone)) {
            JOptionPane.showMessageDialog(this, "Please fill all fields");
            return null;
        }

        if (!Validator.isValidate(Validator.INTEGER, age)) {
            JOptionPane.showMessageDialog(this, "Age must be a number");
            return null;
        }

        if (!Validator.isValidate(Validator.PHONE, phone)) {
            JOptionPane.showMessageDialog(this, "Phone must be a number with 10-11 digits");
            return null;
        }

        if (id.isEmpty()) {
            return new Patient(name, Integer.parseInt(age), gender, address, phone);
        }

        return new Patient(Integer.parseInt(id), name, Integer.parseInt(age), gender, address, phone, healthCondition);
    }

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        Patient patient = getPatientFromForm();

        if (patient == null) {
            return;
        }

        int result = patientService.save(patient);

        if (result > 0) {
            JOptionPane.showMessageDialog(this, "Save successfully");
            loadData(patientService.getAllPatients());
        } else {
            JOptionPane.showMessageDialog(this, "Save failed");
        }

    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int answer = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete?", "Delete", JOptionPane.YES_NO_OPTION);

        if (answer == JOptionPane.NO_OPTION) {
            return;
        }

        int row = tbPatients.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to delete");
            return;
        }

        int id = (int) tbPatients.getValueAt(row, 0);

        int result = patientService.delete(id);

        if (result > 0) {
            JOptionPane.showMessageDialog(this, "Delete successfully");
            loadData(patientService.getAllPatients());
        } else {
            JOptionPane.showMessageDialog(this, "Delete failed");
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        txtId.setText("");
        txtName.setText("");
        txtAge.setText("");
        cbGender.setSelectedIndex(0);
        txtAddress.setText("");
        txtPhone.setText("");
        txtHealthCodition.setText("");
    }//GEN-LAST:event_btnClearActionPerformed

    private void tbPatientsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPatientsMouseClicked
        int row = tbPatients.getSelectedRow();

        if (row >= 0) {
            Object id = tbPatients.getValueAt(row, 0);
            Object name = tbPatients.getValueAt(row, 1);
            Object age = tbPatients.getValueAt(row, 2);
            Object gender = tbPatients.getValueAt(row, 3);
            Object address = tbPatients.getValueAt(row, 4);
            Object phone = tbPatients.getValueAt(row, 5);
            Object healthCondition = tbPatients.getValueAt(row, 6);

            txtId.setText(id != null ? id.toString() : "");
            txtName.setText(name != null ? name.toString() : "");
            txtAge.setText(age != null ? age.toString() : "");
            cbGender.setSelectedItem(gender != null ? gender.toString() : "");
            txtAddress.setText(address != null ? address.toString() : "");
            txtPhone.setText(phone != null ? phone.toString() : "");
            txtHealthCodition.setText(healthCondition != null ? healthCondition.toString() : "");
        }
    }//GEN-LAST:event_tbPatientsMouseClicked


    private void btnPrescriptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrescriptionActionPerformed
        int row = tbPatients.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a patient to view prescription");
            return;
        }

        Integer patientId = (Integer) tbPatients.getValueAt(row, 0);

        DetailPrescriptionView.setPatientId(patientId);
        DetailPrescriptionView detailPrescriptionView = new DetailPrescriptionView();

        detailPrescriptionView.setVisible(true);
    }//GEN-LAST:event_btnPrescriptionActionPerformed

    private void btnMedicationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMedicationActionPerformed
        // TODO add your handling code here:

        MedicationView medicationView = new MedicationView();
        medicationView.setVisible(true);
    }//GEN-LAST:event_btnMedicationActionPerformed

    private void txtKeywordKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKeywordKeyTyped
        // TODO add your handling code here:
        String keyword = txtKeyword.getText().trim().toLowerCase();

        if(keyword.isEmpty()) {
            loadData(patientService.getAllPatients());
            return;
        }

        loadData(patientService.searchBy(keyword));
    }//GEN-LAST:event_txtKeywordKeyTyped

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
            java.util.logging.Logger.getLogger(PatientView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PatientView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PatientView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PatientView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PatientView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnMedication;
    private javax.swing.JButton btnPrescription;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> cbGender;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbPatients;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtAge;
    private javax.swing.JTextField txtHealthCodition;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtKeyword;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPhone;
    // End of variables declaration//GEN-END:variables
}
