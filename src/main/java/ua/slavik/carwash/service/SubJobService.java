package ua.slavik.carwash.service;

import ua.slavik.carwash.model.SubJob;

public interface SubJobService
{
    SubJob getSubJobById(Long id);

    SubJob createSubJob(SubJob subJob);

    SubJob updateSubJob(SubJob subJob);

    void deleteSubJob(Long id);
}
