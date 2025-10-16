package com.project.back_end.services;

import com.project.back_end.models.Doctor;
import com.project.back_end.repo.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    // ✅ 1. Lấy danh sách tất cả bác sĩ
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    // ✅ 2. Lấy lịch trống (availability) của bác sĩ theo ID và ngày
    public ResponseEntity<?> getDoctorAvailability(Long doctorId, String date, String token, String role) {
        // Kiểm tra quyền truy cập
        if (!"doctor".equalsIgnoreCase(role) && !"admin".equalsIgnoreCase(role)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("You are not authorized to view this doctor's availability.");
        }

        // Kiểm tra xem bác sĩ có tồn tại không
        Optional<Doctor> doctorOpt = doctorRepository.findById(doctorId);
        if (doctorOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Doctor not found with ID: " + doctorId);
        }

        // Giả lập dữ liệu — sau này có thể thay bằng table lịch làm việc thật
        LocalDate parsedDate = LocalDate.parse(date);
        List<String> availableSlots = List.of("09:00 AM", "10:00 AM", "02:30 PM", "04:00 PM");

        String message = String.format("Doctor ID: %d | Date: %s | Available Slots: %s",
                doctorId, parsedDate, availableSlots);
        return ResponseEntity.ok(message);
    }

    // ✅ 3. Xác thực đăng nhập của bác sĩ
    public ResponseEntity<?> validateLogin(String email, String password) {
        Optional<Doctor> doctor = doctorRepository.findByEmail(email);
        if (doctor.isPresent() && doctor.get().getPassword().equals(password)) {
            return ResponseEntity.ok("Login successful for doctor: " + doctor.get().getName());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Invalid email or password");
    }

    // ✅ 4. Cập nhật thông tin bác sĩ
    public Doctor updateDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    // ✅ 5. Thêm bác sĩ mới
    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    // ✅ 6. Lấy danh sách bác sĩ đang khả dụng (boolean)
    public List<Doctor> getAvailableDoctors(boolean available) {
        return doctorRepository.findByAvailable(available);
    }
}
