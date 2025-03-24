package com.example.medicine.model;

public class Patient {
    private Integer id;
    private String name;
    private Integer age;
    private String gender;
    private String address;
    private String phone;
    private String healthCondition;
    
    public Patient() {
    }

    public Patient(Integer id, String name, Integer age, String gender, String address, String phone, String healthCondition) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
        this.healthCondition = healthCondition;
    }

    public Patient(String name, Integer age, String gender, String address, String phone) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
    }

    public String getHealthCondition() {
        return healthCondition;
    }

    public void setHealthCondition(String healthCondition) {
        this.healthCondition = healthCondition;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public static Patient mapToPatient(Object[] obj) {
        return new Patient(
                (Integer) obj[0],
                (String) obj[1],
                (Integer) obj[2],
                (String) obj[3],
                (String) obj[4],
                (String) obj[5],
                (String) obj[6]
        );
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
