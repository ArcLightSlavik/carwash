package ua.slavik.carwash.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.slavik.carwash.model.CustomerCarLink;
import ua.slavik.carwash.repository.CustomerCarLinkRepository;
import ua.slavik.carwash.service.CustomerCarLinkService;

@Service
public class CustomerCarLinkServiceImpl implements CustomerCarLinkService {
    private final CustomerCarLinkRepository customerCarLinkRepository;

    @Autowired
    public CustomerCarLinkServiceImpl(CustomerCarLinkRepository customerCarLinkRepository) {
        this.customerCarLinkRepository = customerCarLinkRepository;
    }

    public CustomerCarLink getCustomerCarLinkById(Long id) {
        return customerCarLinkRepository.findById(id).orElse(null);
    }

    public CustomerCarLink createCustomerCarLink(CustomerCarLink customerCarLink) {
        return customerCarLinkRepository.save(customerCarLink);
    }

    public CustomerCarLink updateCustomerCarLink(CustomerCarLink customerCarLink) {
        return customerCarLinkRepository.save(customerCarLink);
    }

    public void deleteCustomerCarLink(Long id) {
        CustomerCarLink customerCarLink = getCustomerCarLinkById(id);
        if (customerCarLink != null) {
            customerCarLinkRepository.deleteById(id);
        }
    }
}
