package ru.itis.rabbitmqpractice.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.rabbitmqpractice.models.User;
import ru.itis.rabbitmqpractice.producers.DismissalProducer;

@RestController
public class DismissalController {

    @Autowired
    private DismissalProducer dismissalProducer;

    @PostMapping("/dismissal")
    public ResponseEntity<String> getPdfForDismissal(@RequestBody User user) {
        dismissalProducer.produce(user, "dismissal");
        return ResponseEntity.ok("Dismissal PDF for " + user.getLastName() + " created");
    }
}
