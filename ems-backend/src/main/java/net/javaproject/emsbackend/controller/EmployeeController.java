package net.javaproject.emsbackend.controller;

import lombok.AllArgsConstructor;
import net.javaproject.emsbackend.dto.EmployeeDto;
import net.javaproject.emsbackend.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    // inject the dependency
    private EmployeeService employeeService;

    //Build add Employee REST API
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    // Build Get Employee Rest API
    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long employeeId){
        EmployeeDto employeeDto=employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok(employeeDto);
    }

    // Build get Rest API
    @GetMapping
    public ResponseEntity<List<EmployeeDto>>getAllEmployees(){
        List<EmployeeDto> employees= employeeService.getAllEmployee();
        return ResponseEntity.ok(employees);
    }

    //build update employee Rest API
    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long employeeId,
                                                      @RequestBody EmployeeDto updateEmployee){
        EmployeeDto employeeDto= employeeService.updateEmployee(employeeId, updateEmployee);
        return ResponseEntity.ok(employeeDto);
    }
    // Build delete Employee method
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId){
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok("Employee delete successfully!. ");
    }

}
