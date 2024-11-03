package com.backend.hmsbackend.doctorlogin.repository;

import com.backend.hmsbackend.doctorlogin.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {
}
