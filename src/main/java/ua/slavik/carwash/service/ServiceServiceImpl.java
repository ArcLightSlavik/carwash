package ua.slavik.carwash.service;

import org.springframework.beans.factory.annotation.Autowired;
import ua.slavik.carwash.model.Service;
import ua.slavik.carwash.repository.ServiceRepository;

import java.util.Optional;

@org.springframework.stereotype.Service

public class ServiceServiceImpl implements ServiceService
{
    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public Service getServiceById(Long id)
    {
        return serviceRepository.findById(id).orElse(null);
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
    public void deleteService(Long id)
    {
        Service service = getServiceById(id);
        if (service != null) {
            serviceRepository.deleteById(id);
        }
    }
}