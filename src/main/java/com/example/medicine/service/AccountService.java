package com.example.medicine.service;

import com.example.medicine.util.DbConnect;

import java.sql.SQLOutput;

public class AccountService {
    private DbConnect dbConnect = DbConnect.getInstance();
    
    public boolean login(String username, String password) {
        String sql = "SELECT * FROM accounts WHERE username = ? AND password = ?";
        return !dbConnect.executeQuery(sql, username, password).isEmpty();
    }

    public static void main(String[] args) {
        AccountService accountService = new AccountService();
        System.out.println(accountService.login("admin", "admin123"));
    }
}
