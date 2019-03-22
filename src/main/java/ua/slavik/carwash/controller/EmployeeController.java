package ua.slavik.carwash.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.slavik.carwash.dto.customer.CustomerDTO;
import ua.slavik.carwash.dto.employee.CreateEmployeeDTO;
import ua.slavik.carwash.dto.employee.EmployeeDTO;
import ua.slavik.carwash.dto.employee.UpdateEmployeeDTO;
import ua.slavik.carwash.model.Employee;
import ua.slavik.carwash.service.EmployeeService;
import javax.validation.Valid;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final ModelMapper modelMapper = new ModelMapper();
    private final EmployeeService employeeService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity createEmployee(@Valid @RequestBody CreateEmployeeDTO employeeDTO) {
        Employee employee = modelMapper.map(employeeDTO, Employee.class);
        Employee savedEmployee = employeeService.createEmployee(employee);

        return new ResponseEntity<>(modelMapper.map(savedEmployee, EmployeeDTO.class), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{employeeid}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getEmployee(@PathVariable("employeeid") Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee == null) {
            return new ResponseEntity<>("Employee by id you entered wasn't found", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(modelMapper.map(employee, EmployeeDTO.class), HttpStatus.OK);
    }

    @PutMapping(value = "/{employeeid}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity updateEmployee(@RequestBody UpdateEmployeeDTO updateEmployeeDTO, @PathVariable("employeeid") Long id) {
        Employee oldEmployee = modelMapper.map(updateEmployeeDTO, Employee.class);
        if (oldEmployee == null) {
            return new ResponseEntity<>("Employee by id you entered wasn't found.", HttpStatus.BAD_REQUEST);
        }
        Employee updatedEmployee = employeeService.updateEmployee(oldEmployee, id);

        return new ResponseEntity<>(modelMapper.map(updatedEmployee, CustomerDTO.class), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{employeeid}")
    public ResponseEntity deleteEmployee(@PathVariable("employeeid") Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee == null) {
            return new ResponseEntity<>("Employee by id you entered wasn't found.", HttpStatus.BAD_REQUEST);
        }
        employeeService.deleteEmployee(id);

        return new ResponseEntity<>("Employee has been deleted.", HttpStatus.OK);
    }
}
