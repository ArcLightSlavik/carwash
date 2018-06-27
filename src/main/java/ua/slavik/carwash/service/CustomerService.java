package ua.slavik.carwash.service;

import ua.slavik.carwash.model.Customer;

public interface CustomerService
{
    Customer getCustomerById(Long id);

    Customer createCustomer(Customer customer);

    Customer updateCustomer(Customer customer);

    void deleteCustomer(Long id);
}