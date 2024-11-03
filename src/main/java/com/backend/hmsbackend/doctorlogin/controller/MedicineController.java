package com.backend.hmsbackend.doctorlogin.controller;

import com.backend.hmsbackend.doctorlogin.entity.Medicine;
import com.backend.hmsbackend.doctorlogin.repository.MedicineRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicine")
public class MedicineController {

    private final MedicineRepository medicineRepository;

    public MedicineController(MedicineRepository medicineRepository) {
        super();
        this.medicineRepository = medicineRepository;
    }

    @PostMapping("/add")
    public Medicine createMedicine(@RequestBody Medicine medicine) {
        return medicineRepository.save(medicine);
    }

    @GetMapping("/list")
    public List<Medicine> getAllMedicine() {
        return medicineRepository.findAll();
    }
}
