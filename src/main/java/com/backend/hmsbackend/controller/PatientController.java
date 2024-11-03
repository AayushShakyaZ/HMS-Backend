package com.backend.hmsbackend.controller;

import com.backend.hmsbackend.model.entity.Patient;
import com.backend.hmsbackend.repository.PatientRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patient")
public class PatientController {

    private final PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository) {
        super();
        this.patientRepository = patientRepository;
    }

    @PostMapping("/add")
    public Patient createPatient(@RequestBody Patient patient) {
        return patientRepository.save(patient);
    }

    @GetMapping("/list")
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }



}
