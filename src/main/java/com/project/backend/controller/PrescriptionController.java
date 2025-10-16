package com.project.backend.controller;

import com.project.backend.model.Prescription;
import com.project.backend.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    // ✅ Endpoint test (giữ lại để check API)
    @GetMapping("/test")
    public String testPrescriptionAPI() {
        return "Prescription API is working!";
    }

    // ✅ Endpoint chính: Tạo đơn thuốc mới (POST)
    @PostMapping
    public ResponseEntity<?> createPrescription(
            @RequestHeader("Authorization") String token,
            @RequestBody Prescription prescription) {

        try {
            // Gọi service để xử lý lưu đơn thuốc
            Prescription savedPrescription = prescriptionService.savePrescription(token, prescription);

            // Trả kết quả JSON về client
            return ResponseEntity.ok(savedPrescription);
        } catch (Exception e) {
            // Nếu có lỗi -> trả lỗi hợp lệ cho client
            return ResponseEntity.badRequest().body("Error creating prescription: " + e.getMessage());
        }
    }
}
