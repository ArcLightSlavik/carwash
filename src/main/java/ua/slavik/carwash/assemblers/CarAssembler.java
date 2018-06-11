package ua.slavik.carwash.assemblers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.slavik.carwash.VO.CarVO;
import ua.slavik.carwash.VO.CreateCarVO;
import ua.slavik.carwash.VO.UpdateCarVO;
import ua.slavik.carwash.model.Car;
import ua.slavik.carwash.model.Job;
import ua.slavik.carwash.service.CustomerService;
import ua.slavik.carwash.service.JobService;

import java.util.ArrayList;
import java.util.List;


@Component
public class CarAssembler
{
    @Autowired
    private CustomerService customerService;

    @Autowired
    private JobService jobService;

    public Car toCar(CreateCarVO createCarVO)
    {
        Car car = new Car();
        car.setCustomer(customerService.getCustomerById(createCarVO.getCustomerId()).orElse(null));
        car.setModel(createCarVO.getModel());
        car.setRegistrationNumber(createCarVO.getRegistrationNumber());

        List<Job> jobs = new ArrayList<Job>();
        for (Long jobId : createCarVO.getJobIds())
        {
            jobs.add(jobService.getJobById(jobId).orElse(null));
        }
        car.setJobs(jobs);

        return car;
    }

    public CarVO toCarVO(Car car)
    {
        CarVO carVO = new CarVO();
        carVO.setId(car.getId());
        carVO.setCustomerId(car.getCustomer().getId());
        carVO.setModel(car.getModel());
        carVO.setRegistrationNumber(car.getRegistrationNumber());

        List<Long> jobIds = new ArrayList<Long>();
        for (Job job : car.getJobs())
        {
            jobIds.add(job.getId());
        }
        carVO.setJobIds(jobIds);

        return carVO;
    }

    public Car toCar(UpdateCarVO updateCarVO)
    {
        Car car = new Car();
        car.setId(updateCarVO.getId());
        car.setCustomer(customerService.getCustomerById(updateCarVO.getCustomerId()).orElse(null));
        car.setModel(updateCarVO.getModel());
        car.setRegistrationNumber(updateCarVO.getRegistrationNumber());

        List<Job> jobs = new ArrayList<Job>();
        for (Long jobId : updateCarVO.getJobIds())
        {
            jobs.add(jobService.getJobById(jobId).orElse(null));
        }
        car.setJobs(jobs);

        return car;
    }
}
