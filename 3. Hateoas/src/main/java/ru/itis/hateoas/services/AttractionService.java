package ru.itis.hateoas.services;

import ru.itis.hateoas.models.Attraction;

public interface AttractionService {
    Attraction updateStatus(Long attractionId, int status);
}
