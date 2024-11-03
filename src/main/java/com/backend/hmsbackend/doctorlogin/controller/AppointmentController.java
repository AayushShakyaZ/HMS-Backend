package com.backend.hmsbackend.doctorlogin.controller;

import com.backend.hmsbackend.doctorlogin.entity.Appointment;
import com.backend.hmsbackend.doctorlogin.repository.AppointmentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {

    private final AppointmentRepository appointmentRepository;

    public AppointmentController(AppointmentRepository appointmentRepository) {
        super();
        this.appointmentRepository = appointmentRepository;
    }

    @PostMapping("/add")
    public Appointment createAppointment(@RequestBody Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @GetMapping("/list")
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }
}
