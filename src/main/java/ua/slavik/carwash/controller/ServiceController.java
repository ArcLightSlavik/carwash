package ua.slavik.carwash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.slavik.carwash.VO.CreateServiceVO;
import ua.slavik.carwash.VO.ServiceVO;
import ua.slavik.carwash.VO.UpdateServiceVO;
import ua.slavik.carwash.assemblers.ServiceAssembler;
import ua.slavik.carwash.model.Service;
import ua.slavik.carwash.service.ServiceService;



@RestController
public class ServiceController
{
    @Autowired
    private ServiceAssembler serviceAssembler;

    @Autowired
    private ServiceService serviceService;

    @RequestMapping(method = RequestMethod.POST)
    public ServiceVO createService(@RequestBody CreateServiceVO serviceVO)
    {
        Service service = serviceAssembler.toService(serviceVO);
        Service savedService = serviceService.createService(service);

        return serviceAssembler.toServiceVO(savedService);
    }

    @RequestMapping(value = "/services/{id}", method = RequestMethod.GET)
    public ServiceVO getService(@PathVariable("id") Long id)
    {
        return serviceAssembler.toServiceVO(serviceService.getServiceById(id));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ServiceVO updateService(@RequestBody UpdateServiceVO updateServiceVO)
    {
        Service service = serviceAssembler.toService(updateServiceVO);
        Service updatedService = serviceService.updateService(service);

        return serviceAssembler.toServiceVO(service);
    }

    @RequestMapping(value = "/services/{id}" , method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id)
    {
        serviceService.deleteService(id);
    }
}
