package ua.slavik.carwash.assemblers;

import org.springframework.stereotype.Component;
import ua.slavik.carwash.VO.CreateServiceVO;
import ua.slavik.carwash.VO.ServiceVO;
import ua.slavik.carwash.VO.UpdateServiceVO;
import ua.slavik.carwash.model.Service;

@Component
public class ServiceAssembler
{
    public Service toService(CreateServiceVO createServiceVO)
    {
        Service service = new Service();
        service.setName(createServiceVO.getName());
        service.setPrice(createServiceVO.getPrice());
        service.setJobs(createServiceVO.getJobs());

        return service;
    }
    public ServiceVO toServiceVO(Service service)
    {
        ServiceVO serviceVO = new ServiceVO();
        serviceVO.setId(service.getId());
        serviceVO.setName(service.getName());
        serviceVO.setPrice(service.getPrice());
        serviceVO.setJobs(service.getJobs());

        return serviceVO;
    }
    public Service toService(UpdateServiceVO updateServiceVO)
    {
        Service service = new Service();
        service.setId(updateServiceVO.getId());
        service.setName(updateServiceVO.getName());
        service.setPrice(updateServiceVO.getPrice());
        service.setJobs(updateServiceVO.getJobs());

        return service;
    }
}
