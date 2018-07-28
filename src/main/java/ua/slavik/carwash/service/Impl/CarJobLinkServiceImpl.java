package ua.slavik.carwash.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.slavik.carwash.model.CarJobLink;
import ua.slavik.carwash.repository.CarJobLinkRepository;
import ua.slavik.carwash.service.CarJobLinkService;

@Service
public class CarJobLinkServiceImpl implements CarJobLinkService {
    private final CarJobLinkRepository carJobLinkRepository;

    @Autowired
    public CarJobLinkServiceImpl(CarJobLinkRepository carJobLinkRepository) {
        this.carJobLinkRepository = carJobLinkRepository;
    }

    public CarJobLink getCarJobLinkById(Long id) {
        return carJobLinkRepository.findById(id).orElse(null);
    }

    public CarJobLink createCarJobLink(CarJobLink carJobLink) {
        return carJobLinkRepository.save(carJobLink);
    }

    public CarJobLink updateCarJobLink(CarJobLink carJobLink) {
        return carJobLinkRepository.save(carJobLink);
    }

    public void deleteCarJobLink(Long id) {
        CarJobLink carJobLink = getCarJobLinkById(id);
        if (carJobLink != null) {
            carJobLinkRepository.deleteById(id);
        }
    }
}