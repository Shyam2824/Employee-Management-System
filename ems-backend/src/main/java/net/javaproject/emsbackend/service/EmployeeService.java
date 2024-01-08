package net.javaproject.emsbackend.service;

import net.javaproject.emsbackend.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployeeById(Long employeeId);
    List<EmployeeDto> getAllEmployee();
    EmployeeDto updateEmployee(Long employeeId , EmployeeDto updateEmployee);
    void deleteEmployee(Long employeeId);
}
