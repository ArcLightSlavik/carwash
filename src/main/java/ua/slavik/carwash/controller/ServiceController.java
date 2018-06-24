package ua.slavik.carwash.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getService(@PathVariable("id") Long id)
    {
        Service service = serviceService.getServiceById(id);
        if (service == null) {
            return new ResponseEntity("Not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(modelMapper.map(service, ServiceDTO.class), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ServiceDTO updateService(@RequestBody UpdateServiceDTO updateServiceVO)
    {
        Service service = modelMapper.map(updateServiceVO , Service.class);
        Service updatedService = serviceService.updateService(service);

        return modelMapper.map(updatedService , ServiceDTO.class);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") Long id)
    {
        Service service = serviceService.getServiceById(id);
        if (service == null)
        {
            return new ResponseEntity("Not found", HttpStatus.NOT_FOUND);
        }
        serviceService.deleteService(id);
        return new ResponseEntity("deleted" , HttpStatus.OK);
    }
}
