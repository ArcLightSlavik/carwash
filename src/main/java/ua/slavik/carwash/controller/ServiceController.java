package ua.slavik.carwash.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.slavik.carwash.VO.CreateServiceVO;
import ua.slavik.carwash.VO.ServiceVO;
import ua.slavik.carwash.VO.UpdateServiceVO;
import ua.slavik.carwash.model.Service;
import ua.slavik.carwash.service.ServiceService;



@RestController
public class ServiceController
{
    private ModelMapper modelMapper =  new ModelMapper();

    @Autowired
    private ServiceService serviceService;

    @RequestMapping(method = RequestMethod.POST)
    public ServiceVO createService(@RequestBody CreateServiceVO serviceVO)
    {
        Service service = modelMapper.map(serviceVO, Service.class);
        Service savedService = serviceService.createService(service);

        return modelMapper.map( savedService, ServiceVO.class);
    }

    @RequestMapping(value = "/services/{id}", method = RequestMethod.GET)
    public ServiceVO getService(@PathVariable("id") Long id)
    {
        return modelMapper.map(serviceService.getServiceById(id), ServiceVO.class);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ServiceVO updateService(@RequestBody UpdateServiceVO updateServiceVO)
    {
        Service service = modelMapper.map(updateServiceVO , Service.class);
        Service updatedService = serviceService.updateService(service);

        return modelMapper.map(updatedService , ServiceVO.class);
    }

    @RequestMapping(value = "/services/{id}" , method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id)
    {
        serviceService.deleteService(id);
    }
}
