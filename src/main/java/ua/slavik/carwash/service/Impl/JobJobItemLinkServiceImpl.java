package ua.slavik.carwash.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.slavik.carwash.model.JobJobItemLink;
import ua.slavik.carwash.repository.JobJobItemLinkRepository;
import ua.slavik.carwash.service.JobJobItemLinkService;

@Service
public class JobJobItemLinkServiceImpl implements JobJobItemLinkService {
    private final JobJobItemLinkRepository jobJobItemLinkRepository;

    @Autowired
    public JobJobItemLinkServiceImpl(JobJobItemLinkRepository jobJobItemLinkRepository) {
        this.jobJobItemLinkRepository = jobJobItemLinkRepository;
    }

    @Override
    public JobJobItemLink getJobJobItemLinkById(Long id) {
        return jobJobItemLinkRepository.findById(id).orElse(null);
    }

    @Override
    public JobJobItemLink createJobJobItemLink(JobJobItemLink jobJobItemLink) {
        return jobJobItemLinkRepository.save(jobJobItemLink);
    }

    @Override
    public JobJobItemLink updateJobJobItemLink(JobJobItemLink jobJobItemLink) {
        return jobJobItemLinkRepository.save(jobJobItemLink);
    }

    @Override
    public void deleteJobJobItemLink(Long id) {
        JobJobItemLink jobJobItemLink = getJobJobItemLinkById(id);
        if (jobJobItemLink != null) {
            jobJobItemLinkRepository.deleteById(id);
        }
    }
}
