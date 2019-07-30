package ua.slavik.carwash.service;

import ua.slavik.carwash.model.Customer;

import java.util.List;

public interface CustomerService {
    Customer getCustomerById(Long id);
    Customer createCustomer(Customer customer);
    Customer updateCustomer(Customer customer, Long id);
    void deleteCustomer(Long id);

    List<Customer> getCustomerContainingGivenString(String givenString);
}
