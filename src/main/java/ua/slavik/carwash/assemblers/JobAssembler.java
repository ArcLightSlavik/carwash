package ua.slavik.carwash.assemblers;

import org.springframework.stereotype.Component;
import ua.slavik.carwash.VO.CreateJobVO;
import ua.slavik.carwash.VO.JobVO;
import ua.slavik.carwash.VO.UpdateJobVO;
import ua.slavik.carwash.model.Job;

@Component
public class JobAssembler
{
    public Job toJob(CreateJobVO createJobVO)
    {
        Job job = new Job();
        job.setServices(createJobVO.getServices());
        job.setCar(createJobVO.getCar());
        job.setCompleted(createJobVO.isCompleted());

        return job;
    }
    public JobVO toJobVO(Job job)
    {
        JobVO jobVO = new JobVO();
        jobVO.setId(job.getId());
        jobVO.setServices(job.getServices());
        jobVO.setCar(job.getCar());
        jobVO.setCompleted(job.isCompleted());

        return jobVO;
    }
    public Job toJob(UpdateJobVO updateJobVO)
    {
        Job job = new Job();
        job.setId(updateJobVO.getId());
        job.setServices(updateJobVO.getServices());
        job.setCar(updateJobVO.getCar());
        job.setCompleted(updateJobVO.isCompleted());

        return job;
    }
}
