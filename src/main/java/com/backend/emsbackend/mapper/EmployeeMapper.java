package com.backend.emsbackend.mapper;

import com.backend.emsbackend.dto.EmployeeDto;
import com.backend.emsbackend.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeMapper {


    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    EmployeeDto toEmployeeDto(Employee employee);

    Employee dtoToEmployee(EmployeeDto employeeDto);


//    public static EmployeeDto mapToEmployeeDto(Employee employee){
//        return new EmployeeDto(
//                employee.getId(),
//                employee.getFirstName(),
//                employee.getLastName(),
//                employee.getEmail()
//        );
//    }
//    public static Employee mapToEmployee(EmployeeDto employeeDto){
//        return new Employee(
//                employeeDto.getId(),
//                employeeDto.getFirstName(),
//                employeeDto.getLastName(),
//                employeeDto.getEmail()
//        );
//    }



}
