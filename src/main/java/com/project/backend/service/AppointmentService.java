package com.project.backend.service;

import com.project.backend.model.Appointment;
import com.project.backend.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * AppointmentService handles all business logic related to appointments.
 * It communicates between controllers and repositories.
 */
@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    /**
     * Get all appointments from the database.
     * @return list of appointments
     */
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    /**
     * Get a specific appointment by its ID.
     * @param id appointment ID
     * @return Optional containing the appointment if found
     */
    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }

    /**
     * Save or update an appointment.
     * @param appointment appointment object
     * @return saved appointment
     */
    public Appointment saveAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    /**
     * Delete an appointment by its ID.
     * @param id appointment ID
     */
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }
}
