package ua.slavik.carwash.assemblers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.slavik.carwash.VO.CreateJobVO;
import ua.slavik.carwash.VO.JobVO;
import ua.slavik.carwash.VO.UpdateJobVO;
import ua.slavik.carwash.model.Job;
import ua.slavik.carwash.model.Service;
import ua.slavik.carwash.service.CarService;
import ua.slavik.carwash.service.ServiceService;

import java.util.ArrayList;
import java.util.List;

@Component
public class JobAssembler
{
    @Autowired
    private ServiceService serviceService;

    @Autowired
    private CarService carService;

    public Job toJob(CreateJobVO createJobVO)
    {
        Job job = new Job();

        List<Service> services = new ArrayList<Service>();
        for (Long serviceId : createJobVO.getServiceIds())
        {
            services.add(serviceService.getServiceById(serviceId));
        }
        job.setServices(services);

        job.setCar(carService.getCarById(createJobVO.getCarId()).orElse(null));
        job.setCompleted(createJobVO.isCompleted());

        return job;
    }
    public JobVO toJobVO(Job job)
    {
        JobVO jobVO = new JobVO();
        jobVO.setId(job.getId());

        List<Long> serviceIds = new ArrayList<Long>();
        for (Service service : job.getServices())
        {
            serviceIds.add(service.getId());
        }
        jobVO.setServiceIds(serviceIds);

        jobVO.setCarId(job.getCar().getId());
        jobVO.setCompleted(job.isCompleted());

        return jobVO;
    }
    public Job toJob(UpdateJobVO updateJobVO)
    {
        Job job = new Job();
        job.setId(updateJobVO.getId());

        List<Service> services = new ArrayList<Service>();
        for (Long serviceId : updateJobVO.getServiceIds())
        {
            services.add(serviceService.getServiceById(serviceId));
        }
        job.setServices(services);

        job.setCar(carService.getCarById(updateJobVO.getCarId()).orElse(null));

        job.setCompleted(updateJobVO.isCompleted());

        return job;
    }
}
