package ua.slavik.carwash.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.slavik.carwash.model.JobTaskLink;
import ua.slavik.carwash.repository.JobTaskLinkRepository;
import ua.slavik.carwash.service.JobTaskLinkService;

@Service
public class JobTaskLinkServiceImpl implements JobTaskLinkService {
    private final JobTaskLinkRepository jobTaskLinkRepository;

    @Autowired
    public JobTaskLinkServiceImpl(JobTaskLinkRepository jobTaskLinkRepository) {
        this.jobTaskLinkRepository = jobTaskLinkRepository;
    }

    @Override
    public JobTaskLink getJobTaskLinkById(Long id) {
        return jobTaskLinkRepository.findById(id).orElse(null);
    }

    @Override
    public JobTaskLink createJobTaskLink(JobTaskLink jobTaskLink) {
        return jobTaskLinkRepository.save(jobTaskLink);
    }
}
