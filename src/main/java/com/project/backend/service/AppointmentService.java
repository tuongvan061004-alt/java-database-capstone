package com.project.backend.service;

import com.project.backend.model.Appointment;
import com.project.backend.model.Doctor;
import com.project.backend.model.Patient;
import com.project.backend.repository.AppointmentRepository;
import com.project.backend.repository.DoctorRepository;
import com.project.backend.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * ✅ AppointmentService
 * Xử lý nghiệp vụ quản lý và đặt lịch hẹn giữa bác sĩ và bệnh nhân.
 */
@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    /**
     * ✅ Lấy tất cả các lịch hẹn trong hệ thống.
     */
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    /**
     * ✅ Tìm lịch hẹn theo ID.
     */
    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }

    /**
     * ✅ Lưu hoặc cập nhật lịch hẹn.
     */
    public Appointment saveAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    /**
     * ✅ Xóa lịch hẹn theo ID.
     */
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }

    /**
     * ✅ Phương thức đặc biệt: ĐẶT LỊCH HẸN (BOOK APPOINTMENT)
     *
     * @param doctorId ID bác sĩ
     * @param patientId ID bệnh nhân
     * @param appointmentTime Thời gian hẹn
     * @param notes Ghi chú thêm (nếu có)
     * @return Appointment mới được tạo và lưu trong DB
     */
    public Appointment bookAppointment(Long doctorId, Long patientId, LocalDateTime appointmentTime, String notes) {
        // 🔹 Kiểm tra bác sĩ tồn tại
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found with ID: " + doctorId));

        // 🔹 Kiểm tra bệnh nhân tồn tại
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalArgumentException("Patient not found with ID: " + patientId));

        // 🔹 Kiểm tra thời gian hợp lệ
        if (appointmentTime.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Appointment time must be in the future.");
        }

        // 🔹 Kiểm tra xem bác sĩ có bị trùng lịch không
        boolean isDoctorBusy = appointmentRepository.existsByDoctorAndAppointmentTime(doctor, appointmentTime);
        if (isDoctorBusy) {
            throw new IllegalStateException("Doctor is not available at this time.");
        }

        // 🔹 Tạo lịch hẹn mới
        Appointment appointment = new Appointment(doctor, patient, appointmentTime, notes);

        // 🔹 Lưu xuống cơ sở dữ liệu
        return appointmentRepository.save(appointment);
    }
}
