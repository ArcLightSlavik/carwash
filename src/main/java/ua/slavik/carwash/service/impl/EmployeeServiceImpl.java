package ua.slavik.carwash.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.slavik.carwash.model.Employee;
import ua.slavik.carwash.repository.EmployeeRepository;
import ua.slavik.carwash.service.EmployeeService;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(NullPointerException::new);
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee, Long id) {
        Employee oldEmployee = getEmployeeById(id);
        if (oldEmployee == null) {
            throw new NullPointerException("You tried to update an entity that didn't exist");
        }

        employee.setId(id);

        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee = getEmployeeById(id);
        if (employee != null) {
            employeeRepository.deleteById(id);
        }
    }

    @Override
    public List<Employee> getEmployeesWithAgeGreaterThan(int age) {
        return employeeRepository.findByAgeGreaterThan(age);
    }
}