package com.backend.hmsbackend.doctorlogin.repository;

import com.backend.hmsbackend.doctorlogin.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
