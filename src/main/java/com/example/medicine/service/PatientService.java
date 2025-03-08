package com.example.medicine.service;

import com.example.medicine.model.Patient;
import com.example.medicine.util.DbConnect;

import java.util.List;

public class PatientService {
    private final DbConnect dbConnect = DbConnect.getInstance();

    public List<Patient> getAllPatients() {
        String sql = "SELECT * FROM patients";
        return dbConnect.executeQuery(sql).stream()
                .map(Patient::mapToPatient)
                .toList();
    }

    public int save(Patient patient) {
        if (patient.getId() == null || !isExist(patient.getId())) {
            String insertSql = "INSERT INTO patients (name, age, gender, address, phone) VALUES (?, ?, ?, ?, ?)";
            return dbConnect.executeUpdate(insertSql,
                    patient.getName(),
                    patient.getAge(),
                    patient.getGender(),
                    patient.getAddress(),
                    patient.getPhone());
        } else {
            String updateSql = "UPDATE patients SET name = ?, age = ?, gender = ?, address = ?, phone = ? WHERE id = ?";
            return dbConnect.executeUpdate(updateSql,
                    patient.getName(),
                    patient.getAge(),
                    patient.getGender(),
                    patient.getAddress(),
                    patient.getPhone(),
                    patient.getId());
        }
    }
    
    public int delete(Integer id) {
        String sql = "DELETE FROM patients WHERE id = ?";
        return dbConnect.executeUpdate(sql, id);
    }
    
    

    public boolean isExist(Integer id) {
        String sql = "SELECT * FROM patients WHERE id = ?";
        return !dbConnect.executeQuery(sql, id).isEmpty();
    }


   public List<Patient> searchBy(String keyword) {
        List<Patient> patients = getAllPatients();

        return patients.stream()
                .filter(patient -> patient.getName().toLowerCase().contains(keyword.toLowerCase())
                        || patient.getAddress().toLowerCase().contains(keyword.toLowerCase())
                        || patient.getPhone().toLowerCase().contains(keyword.toLowerCase())
                        || patient.getGender().toLowerCase().contains(keyword.toLowerCase())
                )
                .toList();
   }
}
