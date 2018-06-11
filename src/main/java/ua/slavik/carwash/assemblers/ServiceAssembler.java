package ua.slavik.carwash.assemblers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.slavik.carwash.VO.CreateServiceVO;
import ua.slavik.carwash.VO.ServiceVO;
import ua.slavik.carwash.VO.UpdateServiceVO;
import ua.slavik.carwash.model.Job;
import ua.slavik.carwash.model.Service;
import ua.slavik.carwash.service.JobService;

import java.util.ArrayList;
import java.util.List;

@Component
public class ServiceAssembler
{
    @Autowired
    private JobService jobService;

    public Service toService(CreateServiceVO createServiceVO)
    {
        Service service = new Service();
        service.setName(createServiceVO.getName());
        service.setPrice(createServiceVO.getPrice());

        List<Job> jobs = new ArrayList<Job>();
        for (Long jobId : createServiceVO.getJobIds()) {
            jobs.add(jobService.getJobById(jobId).orElse(null));
        }
        service.setJobs(jobs);

        return service;
    }

    public ServiceVO toServiceVO(Service service)
    {
        ServiceVO serviceVO = new ServiceVO();
        serviceVO.setId(service.getId());
        serviceVO.setName(service.getName());
        serviceVO.setPrice(service.getPrice());

        List<Long> jobIds = new ArrayList<Long>();
        for (Job job : service.getJobs()) {
            jobIds.add(job.getId());
        }
        serviceVO.setJobIds(jobIds);

        return serviceVO;
    }

    public Service toService(UpdateServiceVO updateServiceVO)
    {
        Service service = new Service();
        service.setId(updateServiceVO.getId());
        service.setName(updateServiceVO.getName());
        service.setPrice(updateServiceVO.getPrice());

        List<Job> jobs = new ArrayList<Job>();
        for (Long jobId : updateServiceVO.getJobIds()) {
            jobs.add(jobService.getJobById(jobId).orElse(null));
        }
        service.setJobs(jobs);

        return service;
    }
}