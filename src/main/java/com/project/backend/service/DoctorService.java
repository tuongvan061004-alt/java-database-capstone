package com.project.back_end.services;

import com.project.back_end.models.Doctor;
import com.project.back_end.repo.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    // ✅ 2. Lấy lịch trống hoặc trạng thái sẵn sàng của bác sĩ
    public List<Doctor> getAvailableDoctors(boolean available) {
        return doctorRepository.findByAvailable(available);
    }

    // ✅ 3. Xác thực đăng nhập của bác sĩ
    public Doctor validateLogin(String email, String password) {
        Optional<Doctor> doctor = doctorRepository.findByEmail(email);
        if (doctor.isPresent() && doctor.get().getPassword().equals(password)) {
            return doctor.get();
        }
        return null;
    }

    // ✅ 4. Cập nhật thông tin bác sĩ
    public Doctor updateDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    // ✅ 5. Thêm bác sĩ mới
    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }
}
