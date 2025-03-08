/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.example.medicine;

import com.example.medicine.service.BackgroundService;
import com.example.medicine.service.MedicationService;
import com.example.medicine.view.LoginView;
import com.formdev.flatlaf.FlatLightLaf;

/**
 * @author ThinhTran
 */
public class Medicine {

    public static void main(String[] args) {
        try {
            javax.swing.UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        
        LoginView loginView = new LoginView();

        loginView.setVisible(true);
    }
}
