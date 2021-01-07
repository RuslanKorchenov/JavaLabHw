package ru.itis.hateoas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.hateoas.services.AttractionService;

@RepositoryRestController
public class AttractionController {

    @Autowired
    private AttractionService attractionService;

    @RequestMapping(value = "/attractions/{attraction-id}/update/{status-code}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<?> updateStatus(@PathVariable("attraction-id") Long attractionId, @PathVariable("status-code") int status) {
        return ResponseEntity.ok(
                EntityModel.of(attractionService.updateStatus(attractionId, status)));

    }
}
