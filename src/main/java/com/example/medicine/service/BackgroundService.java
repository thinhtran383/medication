package com.example.medicine.service;

import com.example.medicine.util.DbConnect;
import com.example.medicine.util.UserContext;

import java.sql.Time;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BackgroundService {
    private final DbConnect dbConnect;
    private final MailService mailService;

    public BackgroundService() {
        this.dbConnect = DbConnect.getInstance();
        this.mailService = new MailService("smtp.gmail.com");
    }

    public void startService() {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        long initialDelay = calculateInitialDelay();

        scheduler.scheduleAtFixedRate(() -> {
            try {
                processMedications();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, initialDelay, TimeUnit.DAYS.toMillis(1), TimeUnit.MILLISECONDS);
    }

    private long calculateInitialDelay() {
        LocalTime now = LocalTime.now();
        LocalTime nextRunTime = LocalTime.MIDNIGHT;
        long delayInSeconds = now.until(nextRunTime.plusHours(24), TimeUnit.SECONDS.toChronoUnit());
        return delayInSeconds * 1000;
    }

    private void processMedications() {
        System.out.println("Processing medications at: " + LocalTime.now());

        String query = """
                    SELECT pm.patient_id, pm.supplement_id, pm.quantity, s.dose_per_day, s.quantity AS supplement_quantity, s.medicine_name
                    FROM PatientMedications pm
                    INNER JOIN Supplements s ON pm.supplement_id = s.id
                    WHERE pm.medication_time <= ?
                """;

        Time currentTime = Time.valueOf(LocalTime.now());
        List<Object[]> medications = dbConnect.executeQuery(query, currentTime);

        String updateQuantityQuery = "UPDATE Supplements SET quantity = quantity - ? WHERE id = ?";
        String updateAvailabilityQuery = "UPDATE Supplements SET availability = ? WHERE id = ?";

        Map<String, String> warningMedications = new LinkedHashMap<>();

        for (Object[] row : medications) {
            Integer patientId = (Integer) row[0];
            Integer supplementId = (Integer) row[1];
            Integer takenQuantity = (Integer) row[2];
            Integer dosePerDay = (Integer) row[3];
            Integer supplementQuantity = (Integer) row[4];
            String medicineName = (String) row[5];

            if (supplementId != null && takenQuantity != null) {
                dbConnect.executeUpdate(updateQuantityQuery, takenQuantity, supplementId);
                System.out.printf("Updated supplement ID %d: Reduced quantity by %d\n", supplementId, takenQuantity);

                int remainingQuantity = supplementQuantity - takenQuantity;

                String newAvailability;
                if (remainingQuantity < 0) {
                    newAvailability = "Out of stock";
                    warningMedications.put(medicineName, "Out of stock");
                } else if (remainingQuantity < dosePerDay * 30) {
                    newAvailability = "30 days left";
                    warningMedications.put(medicineName, "30 days left");
                } else {
                    newAvailability = "Available";
                }

                dbConnect.executeUpdate(updateAvailabilityQuery, newAvailability, supplementId);

                System.out.printf(
                        "Supplement '%s' (ID: %d) for patient ID %d updated availability to '%s'. Remaining: %d\n",
                        medicineName, supplementId, patientId, newAvailability, remainingQuantity
                );
            }
        }

        if (!warningMedications.isEmpty()) {
            String subject = "Medication Stock Alert";
            mailService.sendMailWithTable(warningMedications, subject, UserContext.getInstance().getUsername());
        }
    }
}
