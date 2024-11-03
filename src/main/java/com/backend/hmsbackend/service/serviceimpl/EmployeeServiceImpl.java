package com.backend.hmsbackend.service.serviceimpl;

import com.backend.hmsbackend.model.dto.EmployeeDto;
import com.backend.hmsbackend.model.entity.Employee;
import com.backend.hmsbackend.exceptions.ResourseNotFoundException;
import com.backend.hmsbackend.mapper.EmployeeMapper;
import com.backend.hmsbackend.repository.EmployeeRepository;
import com.backend.hmsbackend.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.INSTANCE.dtoToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.INSTANCE.toEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourseNotFoundException("Employee does not exist for the given id: " + employeeId));
        return EmployeeMapper.INSTANCE.toEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(EmployeeMapper.INSTANCE::toEmployeeDto).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployees(Long employeeId, EmployeeDto updatedEmployee) {

        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourseNotFoundException("Employee does not exist with the given id: " + employeeId)
        );
        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());
        Employee updatedEmployeeObj = employeeRepository.save(employee);
        return EmployeeMapper.INSTANCE.toEmployeeDto(updatedEmployeeObj);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourseNotFoundException("Employee does not exist: " + employeeId)
        );
        employeeRepository.deleteById(employeeId);

    }
}
