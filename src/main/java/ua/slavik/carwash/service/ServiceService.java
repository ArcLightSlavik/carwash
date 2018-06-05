package ua.slavik.carwash.service;

import ua.slavik.carwash.model.Service;
import java.util.Optional;

public interface ServiceService
{
    Optional<Service> getServiceById(Long id);

    Service createService(Service service);

    Service updateService(Service service);

    void deleteService(Long id);
}
