package ru.itis.hateoas.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import ru.itis.hateoas.controllers.AttractionController;
import ru.itis.hateoas.models.Attraction;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AttractionRepresentationProcessor implements RepresentationModelProcessor<EntityModel<Attraction>> {
    @Autowired
    private RepositoryEntityLinks links;

    @Override
    public EntityModel<Attraction> process(EntityModel<Attraction> model) {
        Attraction attraction = model.getContent();
        if (attraction != null && attraction.getStatus().equals(Attraction.Status.AVAILABLE)) {
            model.add(linkTo(methodOn(AttractionController.class)
                    .updateStatus(attraction.getId(), 2)).withRel("setNoTickets"));
            model.add(linkTo(methodOn(AttractionController.class)
                    .updateStatus(attraction.getId(), 3)).withRel("setAsBroken"));
        }
        if (attraction != null && (attraction.getStatus().equals(Attraction.Status.BROKEN)
                        || attraction.getStatus().equals(Attraction.Status.NO_TICKETS))) {
            model.add(linkTo(methodOn(AttractionController.class)
                    .updateStatus(attraction.getId(), 1)).withRel("setAvailable"));
        }
        return model;
    }
}
