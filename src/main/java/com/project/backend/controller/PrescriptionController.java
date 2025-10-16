package com.project.backend.controller;

import com.project.backend.model.Prescription;
import com.project.backend.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * PrescriptionController
 * Quản lý các API liên quan đến đơn thuốc (Prescription).
 * Bao gồm các chức năng: lấy danh sách đơn thuốc, tạo mới đơn thuốc.
 */
@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    /**
     *  GET: Lấy tất cả đơn thuốc
     * @return danh sách các đơn thuốc
     */
    @GetMapping
    public ResponseEntity<List<Prescription>> getAllPrescriptions() {
        List<Prescription> prescriptions = prescriptionService.getAllPrescriptions();
        return new ResponseEntity<>(prescriptions, HttpStatus.OK);
    }

    /**
     *  POST: Tạo (lưu) đơn thuốc mới
     * @param token token xác thực (giả định được gửi qua header)
     * @param prescription thông tin đơn thuốc từ request body
     * @return Prescription vừa được lưu
     */
    @PostMapping
    public ResponseEntity<?> createPrescription(
            @RequestHeader("Authorization") String token,
            @RequestBody Prescription prescription) {

        try {
            ////Kiểm tra token (mô phỏng xác thực)
            if (token == null || !token.startsWith("Bearer ")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Missing or invalid token");
            }

            //// Lưu đơn thuốc qua Service
            Prescription savedPrescription = prescriptionService.savePrescription(prescription);
            return new ResponseEntity<>(savedPrescription, HttpStatus.CREATED);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error saving prescription: " + e.getMessage());
        }
    }

    /**
     *  GET: Kiểm tra API (để test nhanh)
     */
    @GetMapping("/test")
    public String testPrescriptionAPI() {
        return " Prescription API is working!";
    }
}
