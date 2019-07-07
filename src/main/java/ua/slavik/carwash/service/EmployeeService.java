package ua.slavik.carwash.service;

import ua.slavik.carwash.model.Employee;

public interface EmployeeService {
    Employee getEmployeeById(Long id);
    Employee createEmployee(Employee Employee);
    Employee updateEmployee(Employee Employee, Long id);
    void deleteEmployee(Long id);
}
