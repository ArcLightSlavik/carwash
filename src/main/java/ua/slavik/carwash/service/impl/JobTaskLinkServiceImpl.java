package ua.slavik.carwash.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.slavik.carwash.model.JobTaskLink;
import ua.slavik.carwash.repository.JobTaskLinkRepository;
import ua.slavik.carwash.service.JobTaskLinkService;

@Service
@RequiredArgsConstructor
public class JobTaskLinkServiceImpl implements JobTaskLinkService {
    private final JobTaskLinkRepository jobTaskLinkRepository;

    @Override
    public JobTaskLink getJobTaskLinkById(Long id) {
        return jobTaskLinkRepository.findById(id).orElse(null);
    }

    @Override
    public JobTaskLink createJobTaskLink(JobTaskLink jobTaskLink) {
        return jobTaskLinkRepository.save(jobTaskLink);
    }
}
