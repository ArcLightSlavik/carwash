package ua.slavik.carwash.service;

import ua.slavik.carwash.model.JobTaskLink;

public interface JobTaskLinkService {
    JobTaskLink getJobTaskLinkById(Long id);

    JobTaskLink createJobTaskLink(JobTaskLink jobTaskLink);
}
