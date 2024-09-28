package com.backend.emsbackend.repository;

import com.backend.emsbackend.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
