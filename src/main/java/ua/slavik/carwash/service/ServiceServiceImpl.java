package ua.slavik.carwash.service;

import org.springframework.beans.factory.annotation.Autowired;
import ua.slavik.carwash.model.Service;
import ua.slavik.carwash.repository.ServiceRepository;

import java.util.Optional;

public class ServiceServiceImpl implements ServiceService
{
    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public Optional<Service> getServiceById(Long id) {
        return serviceRepository.findById(id);
    }

    @Override
    public Service createService(Service service) {
        return serviceRepository.save(service);
    }

    @Override
    public Service updateService(Service service) {
        return serviceRepository.save(service);
    }

    @Override
    public void deleteService(Long id) {
        Optional<Service> service = getServiceById(id);
        if (service != null) {
            serviceRepository.delete(id);
        }
    }
}