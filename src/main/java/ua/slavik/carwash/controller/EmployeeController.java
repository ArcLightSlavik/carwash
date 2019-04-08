package ua.slavik.carwash.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.slavik.carwash.model.Employee;
import ua.slavik.carwash.model.dto.employee.CreateEmployeeDTO;
import ua.slavik.carwash.model.dto.employee.EmployeeDTO;
import ua.slavik.carwash.model.dto.employee.UpdateEmployeeDTO;
import ua.slavik.carwash.service.EmployeeService;
import javax.validation.Valid;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final ModelMapper modelMapper;
    private final EmployeeService employeeService;

    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity createEmployee(@Valid @RequestBody CreateEmployeeDTO employeeDTO) {
        Employee employee = modelMapper.map(employeeDTO, Employee.class);
        Employee savedEmployee = employeeService.createEmployee(employee);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(modelMapper.map(savedEmployee, EmployeeDTO.class));
    }

    @GetMapping(value = "/{employeeId}", produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getEmployee(@PathVariable("employeeId") Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee == null) {
            return new ResponseEntity<>("Employee by id you entered wasn't found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(modelMapper.map(employee, EmployeeDTO.class));
    }

    @PutMapping(value = "/{employeeId}", consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity updateEmployee(@RequestBody UpdateEmployeeDTO updateEmployeeDTO, @PathVariable("employeeId") Long id) {
        Employee oldEmployee = modelMapper.map(updateEmployeeDTO, Employee.class);
        if (oldEmployee == null) {
            return new ResponseEntity<>("Employee by id you entered wasn't found.", HttpStatus.NOT_FOUND);
        }
        Employee updatedEmployee = employeeService.updateEmployee(oldEmployee, id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(modelMapper.map(updatedEmployee, EmployeeDTO.class));
    }

    @DeleteMapping(value = "/{employeeId}")
    public ResponseEntity deleteEmployee(@PathVariable("employeeId") Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee == null) {
            return new ResponseEntity<>("Employee by id you entered wasn't found.", HttpStatus.NOT_FOUND);
        }
        employeeService.deleteEmployee(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body("Employee has been deleted.");
    }
}
