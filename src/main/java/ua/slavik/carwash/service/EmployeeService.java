package ua.slavik.carwash.service;

import ua.slavik.carwash.model.Employee;

public interface EmployeeService {
    Employee getEmployeeById(Long id);
    Employee createEmployee(Employee employee);
    Employee updateEmployee(Employee employee, Long id);
    void deleteEmployee(Long id);
}
