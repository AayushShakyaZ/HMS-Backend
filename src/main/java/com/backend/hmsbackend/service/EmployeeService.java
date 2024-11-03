package com.backend.hmsbackend.service;

import com.backend.hmsbackend.model.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    EmployeeDto createEmployee(EmployeeDto employeeDto);
    EmployeeDto getEmployeeById(Long employeeId);
    List<EmployeeDto> getAllEmployees();
    EmployeeDto updateEmployees(Long employeeId, EmployeeDto updatedEmployee);

    void deleteEmployee(Long employeeId);

}
