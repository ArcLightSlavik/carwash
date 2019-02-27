package ua.slavik.carwash.service;

import ua.slavik.carwash.model.JobJobItemLink;

public interface JobJobItemLinkService {
    JobJobItemLink getJobJobItemLinkById(Long id);

    JobJobItemLink createJobJobItemLink(JobJobItemLink jobJobItemLink);
}
