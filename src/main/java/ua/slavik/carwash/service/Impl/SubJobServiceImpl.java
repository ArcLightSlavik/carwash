package ua.slavik.carwash.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.slavik.carwash.model.SubJob;
import ua.slavik.carwash.repository.SubJobRepository;
import ua.slavik.carwash.service.SubJobService;

@Service
public class SubJobServiceImpl implements SubJobService
{
    @Autowired
    private SubJobRepository subJobRepository;

    @Override
    public SubJob getSubJobById(Long id)
    {
        return subJobRepository.findById(id).orElse(null);
    }

    @Override
    public SubJob createSubJob(SubJob subJob)
    {
        return subJobRepository.save(subJob);
    }

    @Override
    public SubJob updateSubJob(SubJob subJob)
    {
        return subJobRepository.save(subJob);
    }

    @Override
    public void deleteSubJob(Long id)
    {
        SubJob subJob = getSubJobById(id);
        if (subJob != null)
        {
            subJobRepository.deleteById(id);
        }
    }
}
