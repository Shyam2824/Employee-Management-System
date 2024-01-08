package net.javaproject.emsbackend.service.impl;

import lombok.AllArgsConstructor;
import net.javaproject.emsbackend.dto.EmployeeDto;
import net.javaproject.emsbackend.entity.Employee;
import net.javaproject.emsbackend.exception.ResourceNotFoundException;
import net.javaproject.emsbackend.mapper.EmployeeMapper;
import net.javaproject.emsbackend.repositary.EmployeeRepository;
import net.javaproject.emsbackend.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.text.Collator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        Employee employee= EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee= employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
       Employee employee= employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee is not exist given id:" + employeeId));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployee() {
        List<Employee> employees= employeeRepository.findAll();
        return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updateEmployee) {

        Employee employee=employeeRepository.findById(employeeId).orElseThrow(()
                -> new ResourceNotFoundException("Employee is not exist in given id:" + employeeId));
        employee.setFirstName(updateEmployee.getFirstName());
        employee.setLastName(updateEmployee.getLastName());
        employee.setEmail(updateEmployee.getEmail());

        Employee updateEmployeeObj =employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(updateEmployeeObj);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee=employeeRepository.findById(employeeId).orElseThrow(()
                -> new ResourceNotFoundException("Employee is not exist in given id:" + employeeId));
        employeeRepository.deleteById(employeeId);
    }
}
