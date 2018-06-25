package ua.slavik.carwash.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import CreateServiceDTO;
import ua.slavik.carwash.DTO.ServiceDTO.ServiceDTO;
import ua.slavik.carwash.DTO.ServiceDTO.UpdateServiceDTO;
import ua.slavik.carwash.model.Service;
import ua.slavik.carwash.service.ServiceService;



@RestController
public class ServiceController
{
    private ModelMapper modelMapper =  new ModelMapper();

    @Autowired
    private ServiceService serviceService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createService(@RequestBody CreateServiceDTO serviceDTO)
    {
        Service service = modelMapper.map(serviceDTO, Service.class);
        Service savedService = serviceService.createService(service);

        return new ResponseEntity(modelMapper.map( savedService, ServiceDTO.class), HttpStatus.OK);
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
    public ResponseEntity updateService(@RequestBody UpdateServiceDTO updateServiceVO)
    {
        Service service = modelMapper.map(updateServiceVO , Service.class);
        Service updatedService = serviceService.updateService(service);

        return new ResponseEntity(modelMapper.map(updatedService , ServiceDTO.class), HttpStatus.OK);
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
