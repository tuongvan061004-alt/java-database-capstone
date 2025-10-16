package com.project.backend.controller;

import com.project.backend.model.Doctor;
import com.project.backend.service.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    // ✅ Lấy danh sách tất cả bác sĩ
    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    // ✅ Thêm bác sĩ mới
    @PostMapping
    public Doctor addDoctor(@RequestBody Doctor doctor) {
        return doctorService.saveDoctor(doctor);
    }

    // ✅ Lấy lịch làm việc (availability) của một bác sĩ cụ thể
    // theo yêu cầu rubric (bao gồm doctorId, date, token, role)
    @GetMapping("/{doctorId}/availability")
    public ResponseEntity<?> getDoctorAvailability(
            @RequestHeader("Authorization") String token,
            @PathVariable Long doctorId,
            @RequestParam String date,
            @RequestParam String role) {

        // Gọi service xử lý logic
        return doctorService.getDoctorAvailability(doctorId, date, token, role);
    }
}
