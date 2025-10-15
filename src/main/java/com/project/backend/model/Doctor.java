package com.project.backend.model;

public class Doctor {
    private int doctorId;
    private String name;
    private String specialization;
    private String email;
    private String password;

    public Doctor() {}

    public Doctor(int doctorId, String name, String specialization, String email, String password) {
        this.doctorId = doctorId;
        this.name = name;
        this.specialization = specialization;
        this.email = email;
        this.password = password;
    }

    public int getDoctorId() { return doctorId; }
    public void setDoctorId(int doctorId) { this.doctorId = doctorId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
