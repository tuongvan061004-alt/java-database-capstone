package com.project.backend.service;

import com.project.backend.model.Doctor;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class DoctorService {
    private final List<Doctor> doctors = new ArrayList<>();

    public List<Doctor> getAllDoctors() {
        return doctors;
    }

    public Doctor saveDoctor(Doctor doctor) {
        doctors.add(doctor);
        return doctor;
    }
}
