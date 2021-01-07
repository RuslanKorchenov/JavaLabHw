package ru.itis.rabbitmqpractice.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.rabbitmqpractice.models.User;
import ru.itis.rabbitmqpractice.producers.DismissalProducer;
import ru.itis.rabbitmqpractice.producers.GoodWorkerProducer;

@RestController
public class GoodWorkerController {

    @Autowired
    private GoodWorkerProducer goodWorkerProducer;

    @PostMapping("/end")
    public ResponseEntity<String> getPdfForDismissal(@RequestBody User user) {
        goodWorkerProducer.produce(user);
        return ResponseEntity.ok("Mission completed " + user.getLastName() + "!");
    }
}
