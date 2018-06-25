package ua.slavik.carwash.service;

import ua.slavik.carwash.model.Service;

public interface ServiceService
{
    Service getServiceById(Long id);

    Service createService(Service service);

    Service updateService(Service service);

    void deleteService(Long id);
}
