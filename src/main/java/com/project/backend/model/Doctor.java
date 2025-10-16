package com.project.back_end.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Doctor {

    // ✅ Khóa chính (primary key)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ✅ Các trường thông tin
    private String name;
    private String specialization;
    private String email;
    private String password;
    private boolean available;

    // ✅ Constructor mặc định (bắt buộc cho JPA)
    public Doctor() {
    }

    // ✅ Constructor đầy đủ (tùy chọn)
    public Doctor(String name, String specialization, String email, String password, boolean available) {
        this.name = name;
        this.specialization = specialization;
        this.email = email;
        this.password = password;
        this.available = available;
    }

    // ✅ Getter và Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

