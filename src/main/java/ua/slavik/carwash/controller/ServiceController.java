package ua.slavik.carwash.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.slavik.carwash.DTO.CreateServiceDTO;
import ua.slavik.carwash.DTO.ServiceDTO;
import ua.slavik.carwash.DTO.UpdateServiceDTO;
import ua.slavik.carwash.model.Service;
import ua.slavik.carwash.service.ServiceService;



@RestController
public class ServiceController
{
    private ModelMapper modelMapper =  new ModelMapper();

    @Autowired
    private ServiceService serviceService;

    @RequestMapping(method = RequestMethod.POST)
    public ServiceDTO createService(@RequestBody CreateServiceDTO serviceVO)
    {
        Service service = modelMapper.map(serviceVO, Service.class);
        Service savedService = serviceService.createService(service);

        return modelMapper.map( savedService, ServiceDTO.class);
    }

    @RequestMapping(value = "/services/{id}", method = RequestMethod.GET)
    public ServiceDTO getService(@PathVariable("id") Long id)
    {
        return modelMapper.map(serviceService.getServiceById(id), ServiceDTO.class);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ServiceDTO updateService(@RequestBody UpdateServiceDTO updateServiceVO)
    {
        Service service = modelMapper.map(updateServiceVO , Service.class);
        Service updatedService = serviceService.updateService(service);

        return modelMapper.map(updatedService , ServiceDTO.class);
    }

    @RequestMapping(value = "/services/{id}" , method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id)
    {
        serviceService.deleteService(id);
    }
}
