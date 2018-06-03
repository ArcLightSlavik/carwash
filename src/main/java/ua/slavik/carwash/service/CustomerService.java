package ua.slavik.carwash.service;

import ua.slavik.carwash.model.Customer;

import java.util.Optional;

public interface CustomerService
{
    Optional<Customer> getCustomerById(Long id);

    Customer createCustomer(Customer customer);

    Customer updateCustomer(Customer customer);

    void deleteUser(Long id);
}
