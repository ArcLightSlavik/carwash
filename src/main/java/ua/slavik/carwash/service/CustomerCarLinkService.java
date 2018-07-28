package ua.slavik.carwash.service;

import ua.slavik.carwash.model.CustomerCarLink;

public interface CustomerCarLinkService {
    CustomerCarLink getCustomerCarLinkById(Long id);

    CustomerCarLink createCustomerCarLink(CustomerCarLink customerCarLink);

    CustomerCarLink updateCustomerCarLink(CustomerCarLink customerCarLink);

    void deleteCustomerCarLink(Long id);
}
