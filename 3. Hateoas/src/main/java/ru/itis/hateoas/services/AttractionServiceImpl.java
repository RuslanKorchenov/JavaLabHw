package ru.itis.hateoas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Attr;
import ru.itis.hateoas.models.Attraction;
import ru.itis.hateoas.repositories.AttractionRepository;

@Service
public class AttractionServiceImpl implements AttractionService {

    @Autowired
    private AttractionRepository attractionRepository;
    @Override
    public Attraction updateStatus(Long attractionId, int status) {
        Attraction attraction = attractionRepository.findById(attractionId).orElseThrow(IllegalArgumentException::new);
        attraction.updateStatus(status);
        attractionRepository.save(attraction);
        return attraction;
    }
}
