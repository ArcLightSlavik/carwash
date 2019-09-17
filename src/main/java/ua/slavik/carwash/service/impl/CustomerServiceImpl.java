package ua.slavik.carwash.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.slavik.carwash.exception.custom.NotFoundException;
import ua.slavik.carwash.model.Customer;
import ua.slavik.carwash.repository.CustomerRepository;
import ua.slavik.carwash.service.CustomerService;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer, Long id) {
        getCustomerById(id);
        customer.setId(id);
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        getCustomerById(id);
        customerRepository.deleteById(id);
    }

    @Override
    public List<Customer> getCustomersByFirstNameContainingGivenString(String givenString) {
        List<Customer> customerList = customerRepository.findByFirstNameContaining(givenString);
        if (customerList.size() == 0) {
            throw new NotFoundException();
        }
        return customerList;
    }
}
