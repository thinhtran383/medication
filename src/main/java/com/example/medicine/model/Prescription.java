package com.example.medicine.model;

import java.sql.Time;
import java.time.Instant;

public class Prescription {
    private Integer id;
    private Integer patientId;
    private Integer supplementId;
    private Time time;
    private Integer quantity;
    private String patientName;
    private String medicineName;

    public Prescription() {
    }

    public Prescription(Integer id, Integer patientId, Integer supplementId, Time time, Integer quantity, String patientName, String medicineName) {
        this.id = id;
        this.patientId = patientId;
        this.supplementId = supplementId;
        this.time = time;
        this.quantity = quantity;
        this.patientName = patientName;
        this.medicineName = medicineName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Integer getSupplementId() {
        return supplementId;
    }

    public void setSupplementId(Integer supplementId) {
        this.supplementId = supplementId;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public static Prescription mapToPrescription(Object[] obj) {
        return new Prescription(
                (Integer) obj[0],
                (Integer) obj[1],
                (Integer) obj[2],
                (Time) obj[3],
                (Integer) obj[4],
                (String) obj[5],
                (String) obj[6]
        );
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "id=" + id +
                ", patientId=" + patientId +
                ", supplementId=" + supplementId +
                ", time=" + time +
                ", quantity=" + quantity +
                ", patientName='" + patientName + '\'' +
                ", medicineName='" + medicineName + '\'' +
                '}';
    }
}
