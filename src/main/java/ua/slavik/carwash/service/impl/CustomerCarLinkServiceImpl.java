package ua.slavik.carwash.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.slavik.carwash.model.CustomerCarLink;
import ua.slavik.carwash.repository.CustomerCarLinkRepository;
import ua.slavik.carwash.service.CustomerCarLinkService;

@Service
@RequiredArgsConstructor
public class CustomerCarLinkServiceImpl implements CustomerCarLinkService {
    private final CustomerCarLinkRepository customerCarLinkRepository;

    @Override
    public CustomerCarLink getCustomerCarLinkById(Long id) {
        return customerCarLinkRepository.findById(id).orElse(null);
    }

    @Override
    public CustomerCarLink createCustomerCarLink(CustomerCarLink customerCarLink) {
        return customerCarLinkRepository.save(customerCarLink);
    }
}
