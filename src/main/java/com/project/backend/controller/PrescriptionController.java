package com.project.backend.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    @GetMapping("/test")
    public String testPrescriptionAPI() {
        return "Prescription API is working!";
    }
}
