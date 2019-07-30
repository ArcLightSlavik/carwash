package ua.slavik.carwash.service;

import ua.slavik.carwash.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee getEmployeeById(Long id);
    Employee createEmployee(Employee employee);
    Employee updateEmployee(Employee employee, Long id);
    void deleteEmployee(Long id);

    List<Employee> getEmployeeWithAgeGreaterThan(int age);
}
