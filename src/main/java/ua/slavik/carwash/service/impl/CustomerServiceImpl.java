package ua.slavik.carwash.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.slavik.carwash.model.Customer;
import ua.slavik.carwash.repository.CustomerRepository;
import ua.slavik.carwash.service.CustomerService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer, Long id) {
        Customer oldCustomer = getCustomerById(id);
        if (oldCustomer == null) {
            return null;
        }

        customer.setId(id);

        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        Customer customer = getCustomerById(id);
        if (customer != null) {
            customerRepository.deleteById(id);
        }
    }

    @Override
    public List<Customer> getCustomersByFirstNameContainingGivenString(String givenString) {
        return customerRepository.findByFirstNameContaining(givenString);
    }
}
