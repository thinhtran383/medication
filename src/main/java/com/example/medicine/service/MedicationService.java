package com.example.medicine.service;

import com.example.medicine.model.Supplement;
import com.example.medicine.util.DbConnect;

import java.time.LocalDate;
import java.util.List;

public class MedicationService {
    private final DbConnect dbConnect = DbConnect.getInstance();

    public List<Supplement> getAllSupplements() {
        String sql = """
                SELECT
                                  s.id AS supplement_id,
                                  s.medicine_name,
                                  s.quantity AS stock_quantity,
                                  s.dose_per_day,
                                  s.dosage,
                                  s.expired_date,
                                  s.availability,
                                  CASE
                                      WHEN s.dose_per_day = 0 THEN NULL
                                      ELSE FLOOR(s.quantity / s.dose_per_day)
                                  END AS estimated_days_left,
                                   s.open_date,
                                   s.status
                              FROM Supplements s
                              ORDER BY
                                  CASE
                                      WHEN s.availability = 'Out of stock' THEN 1
                                      WHEN s.availability = '30 days left' THEN 2
                                      WHEN s.availability = 'Available' THEN 3
                                      ELSE 4
                                  END;
                
                                """;
        return dbConnect.executeQuery(sql).stream()
                .map(Supplement::mapToSupplement)
                .toList();
    }

    public int save(Supplement supplement) {
        System.out.println(supplement);

        if (supplement.getId() == null || !isExist(supplement.getId())) {
            String insertSql = "INSERT INTO supplements (medicine_name, quantity, status, " +
                    "dose_per_day, dosage, expired_date, availability) VALUES (?, ?, ?, ?, ?, ?, ?)";
            return dbConnect.executeUpdate(
                    insertSql,
                    supplement.getMedicineName(),
                    supplement.getQuantity(),
                    supplement.getStatus(),
                    supplement.getDosePerDay(),
                    supplement.getDosage(),
                    supplement.getExpirationDate(),
                    supplement.getAvailability()
            );
        } else {
            boolean isOpened = "Opened".equalsIgnoreCase(supplement.getStatus());
            String updateSql = "UPDATE supplements SET medicine_name = ?, status = ?, " +
                    "quantity = ?, dose_per_day = ?, dosage = ?, expired_date = ?, availability = ? " +
                    (isOpened ? ", open_date = ? " : "") +
                    "WHERE id = ?";

            if (isOpened) {
                return dbConnect.executeUpdate(
                        updateSql,
                        supplement.getMedicineName(),
                        supplement.getStatus(),
                        supplement.getQuantity(),
                        supplement.getDosePerDay(),
                        supplement.getDosage(),
                        supplement.getExpirationDate(),
                        supplement.getAvailability(),
                        LocalDate.now(),
                        supplement.getId()
                );
            } else {
                return dbConnect.executeUpdate(
                        updateSql,
                        supplement.getMedicineName(),
                        supplement.getStatus(),
                        supplement.getQuantity(),
                        supplement.getDosePerDay(),
                        supplement.getDosage(),
                        supplement.getExpirationDate(),
                        supplement.getAvailability(),
                        supplement.getId()
                );
            }
        }
    }


    public int updateAvailability(int id, String availability) {
        String sql = "UPDATE supplements SET availability = ? WHERE id = ?";
        return dbConnect.executeUpdate(sql, availability, id);
    }

    public boolean isExist(int id) {
        String sql = "SELECT * FROM supplements WHERE id = ?";
        return !dbConnect.executeQuery(sql, id).isEmpty();
    }




    public int delete(int id) {
        String sql = "DELETE FROM supplements WHERE id = ?";
        return dbConnect.executeUpdate(sql, id);
    }

    public List<Supplement> searchBy(String keyword) {
        List<Supplement> supplements = getAllSupplements();

        return supplements.stream()
                .filter(supplement -> supplement.getMedicineName().toLowerCase().contains(keyword.toLowerCase())
                        || supplement.getDosage().toLowerCase().contains(keyword.toLowerCase())
                        || supplement.getAvailability().toLowerCase().contains(keyword.toLowerCase())
                )
                .toList();
    }
}
