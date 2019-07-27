package ua.slavik.carwash.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.slavik.carwash.model.Employee;
import ua.slavik.carwash.model.dto.employee.CreateEmployeeDTO;
import ua.slavik.carwash.model.dto.employee.EmployeeDTO;
import ua.slavik.carwash.model.dto.employee.UpdateEmployeeDTO;
import ua.slavik.carwash.service.EmployeeService;

import javax.validation.Valid;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeController {
    private final ModelMapper modelMapper;
    private final EmployeeService employeeService;
    private static final String EMPLOYEE_NOT_FOUND = "Employee by id you entered wasn't found.";

    @PostMapping
    public ResponseEntity createEmployee(@Valid @RequestBody CreateEmployeeDTO employeeDTO) {
        Employee employee = modelMapper.map(employeeDTO, Employee.class);
        Employee savedEmployee = employeeService.createEmployee(employee);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(modelMapper.map(savedEmployee, EmployeeDTO.class));
    }

    @GetMapping(value = "/{employeeId}")
    public ResponseEntity getEmployee(@PathVariable("employeeId") Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(EMPLOYEE_NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(modelMapper.map(employee, EmployeeDTO.class));
    }

    @PutMapping(value = "/{employeeId}")
    public ResponseEntity updateEmployee(@RequestBody UpdateEmployeeDTO updateEmployeeDTO, @PathVariable("employeeId") Long id) {
        Employee oldEmployee = modelMapper.map(updateEmployeeDTO, Employee.class);
        if (oldEmployee == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(EMPLOYEE_NOT_FOUND);
        }
        Employee updatedEmployee = employeeService.updateEmployee(oldEmployee, id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(modelMapper.map(updatedEmployee, EmployeeDTO.class));
    }

    @DeleteMapping(value = "/{employeeId}")
    public ResponseEntity deleteEmployee(@PathVariable("employeeId") Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(EMPLOYEE_NOT_FOUND);
        }
        employeeService.deleteEmployee(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body("Employee has been deleted.");
    }
}
