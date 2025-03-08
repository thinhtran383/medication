package com.example.medicine.model;

import java.sql.Date;
import java.time.LocalDate;

public class Supplement {
    private Integer id;
    private String medicineName;
    private Integer quantity;
    private Integer dosePerDay;
    private String dosage;
    private LocalDate expirationDate;
    private String availability;
    private Long estimatedDaysLeft;
    private Date openDate;

    public Supplement() {
    }

    public Supplement(Integer id, String medicineName, Integer quantity, Integer dosePerDay, String dosage, LocalDate expirationDate, String availability, Long estimatedDaysLeft) {
        this.id = id;
        this.medicineName = medicineName;
        this.quantity = quantity;
        this.dosePerDay = dosePerDay;
        this.dosage = dosage;
        this.expirationDate = expirationDate;
        this.availability = availability;
        this.estimatedDaysLeft = estimatedDaysLeft;
    }

    public Supplement(Integer id, String medicineName,
                      Integer quantity, Integer dosePerDay, String dosage, LocalDate expirationDate, String availability, Long estimatedDaysLeft, Date openDate) {
        this.id = id;
        this.medicineName = medicineName;
        this.quantity = quantity;
        this.dosePerDay = dosePerDay;
        this.dosage = dosage;
        this.expirationDate = expirationDate;
        this.availability = availability;
        this.estimatedDaysLeft = estimatedDaysLeft;
        this.openDate = openDate;
    }

    public Supplement(String medicineName, Integer quantity, Integer dosePerDay, String dosage, LocalDate expirationDate, String availability) {
        this.medicineName = medicineName;
        this.quantity = quantity;
        this.dosePerDay = dosePerDay;
        this.dosage = dosage;
        this.expirationDate = expirationDate;
        this.availability = availability;
    }


    public Long getEstimatedDaysLeft() {
        return estimatedDaysLeft;
    }

    public void setEstimatedDaysLeft(Long estimatedDaysLeft) {
        this.estimatedDaysLeft = estimatedDaysLeft;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }



    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getDosePerDay() {
        return dosePerDay;
    }

    public void setDosePerDay(Integer dosePerDay) {
        this.dosePerDay = dosePerDay;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public static Supplement mapToSupplement(Object[] row) {
        Supplement supplement = new Supplement();
        supplement.setId((int) row[0]);
        supplement.setMedicineName((String) row[1]);
        supplement.setQuantity((Integer) row[2]);
        supplement.setDosePerDay((Integer) row[3]);
        supplement.setDosage((String) row[4]);
        supplement.setExpirationDate(((Date) row[5]).toLocalDate());
        supplement.setAvailability((String) row[6]);
        supplement.setEstimatedDaysLeft((Long) row[7]);
        supplement.setOpenDate((Date) row[8]);
        return supplement;
    }

    @Override
    public String toString() {
        return "Supplement{" +
                "id=" + id +
                ", medicineName='" + medicineName + '\'' +
                ", quantity=" + quantity +
                ", dosePerDay=" + dosePerDay +
                ", dosage='" + dosage + '\'' +
                ", expirationDate=" + expirationDate +
                ", availability='" + availability + '\'' +
                ", estimatedDaysLeft=" + estimatedDaysLeft +
                '}';
    }
}
