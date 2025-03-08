package com.example.medicine.service;

import com.example.medicine.model.Prescription;
import com.example.medicine.util.DbConnect;

import java.time.LocalDateTime;
import java.util.List;

public class PrescriptionService {
    private final DbConnect dbConnect = DbConnect.getInstance();

    public List<Prescription> getPrescriptionByPatient(Integer patientId) {
        String sql = """
                select pm.*, p.name, S.medicine_name from PatientMedications pm
                 join Patients P on P.id = pm.patient_id
                left join Supplements S on S.id = pm.supplement_id
                where p.id=?;
                """;
        return dbConnect.executeQuery(sql, patientId).stream()
                .map(Prescription::mapToPrescription)
                .toList();
    }

    public boolean isExistedTime(String time, Integer patientId) {
        String sql = "SELECT * FROM PatientMedications WHERE medication_time = ? and patient_id = ?";
        return !dbConnect.executeQuery(sql, time, patientId).isEmpty();
    }

    public List<String> getAllMedicineNames(Integer patientId) {
        String sql = """
                SELECT s.id AS supplement_id, s.medicine_name
                FROM Supplements s
                WHERE NOT EXISTS (
                    SELECT 1
                    FROM PatientMedications pm
                    WHERE pm.supplement_id = s.id
                      AND pm.patient_id = ?
                );
                """;
        return dbConnect.executeQuery(sql, patientId).stream()
                .map(m -> String.format("%d - %s", (Integer) m[0], (String) m[1]))
                .toList();
    }

    public int addMedicine(Integer medicineId, Integer patientId) {
        String sql = "INSERT INTO PatientMedications (patient_id, supplement_id) VALUES (?, ?)";
        return dbConnect.executeUpdate(sql, patientId, medicineId);
    }

    public int addTime(Integer patientId, String time) {
        String sql = "INSERT INTO PatientMedications (patient_id, medication_time) VALUES (?, ?)";
        return dbConnect.executeUpdate(sql, patientId, time);
    }


    public int updateQuantity(Integer quantity, Integer patientId, String medicineName, String time) {
        String selectSupplementId = """
                    SELECT id FROM Supplements WHERE medicine_name = ?
                """;

        List<Object[]> supplementResult = dbConnect.executeQuery(selectSupplementId, medicineName);

        if (supplementResult.isEmpty()) {
            System.out.println("Medicine not found: " + medicineName);
            return 0;
        }

        Integer supplementId = (Integer) supplementResult.get(0)[0];

        String updateStatus = """
                    UPDATE Supplements 
                    set open_date = ?
                    WHERE open_date IS NULL
                      AND id = ?
                """;

        dbConnect.executeUpdate(updateStatus, LocalDateTime.now(), supplementId);

        String selectQuery = """
                    SELECT id FROM PatientMedications 
                    WHERE patient_id = ? 
                      AND supplement_id = ?
                      AND medication_time = ?
                """;

        List<Object[]> existingRecord = dbConnect.executeQuery(selectQuery, patientId, supplementId, time);

        if (!existingRecord.isEmpty()) {
            String updateQuery = """
                        UPDATE PatientMedications
                        SET quantity = ?
                        WHERE patient_id = ? 
                          AND supplement_id = ?
                          AND medication_time = ?
                    """;
            return dbConnect.executeUpdate(updateQuery, quantity, patientId, supplementId, time);
        } else {
            String insertQuery = """
                        INSERT INTO PatientMedications (patient_id, supplement_id, medication_time, quantity)
                        VALUES (?, ?, ?, ?)
                    """;
            return dbConnect.executeUpdate(insertQuery, patientId, supplementId, time, quantity);
        }
    }


}
