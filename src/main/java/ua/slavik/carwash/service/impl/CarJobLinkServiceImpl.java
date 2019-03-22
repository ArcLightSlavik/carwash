package ua.slavik.carwash.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.slavik.carwash.model.CarJobLink;
import ua.slavik.carwash.repository.CarJobLinkRepository;
import ua.slavik.carwash.service.CarJobLinkService;

@Service
@RequiredArgsConstructor
public class CarJobLinkServiceImpl implements CarJobLinkService {
    private final CarJobLinkRepository carJobLinkRepository;

    @Override
    public CarJobLink getCarJobLinkById(Long id) {
        return carJobLinkRepository.findById(id).orElse(null);
    }

    @Override
    public CarJobLink createCarJobLink(CarJobLink carJobLink) {
        return carJobLinkRepository.save(carJobLink);
    }
}