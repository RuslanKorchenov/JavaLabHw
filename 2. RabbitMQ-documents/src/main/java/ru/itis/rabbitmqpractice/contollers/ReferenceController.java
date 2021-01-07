package ru.itis.rabbitmqpractice.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.rabbitmqpractice.models.User;
import ru.itis.rabbitmqpractice.producers.DismissalProducer;
import ru.itis.rabbitmqpractice.producers.ReferenceProducer;

@RestController
public class ReferenceController {
    @Autowired
    private ReferenceProducer referenceProducer;

    @PostMapping("/reference/income")
    public ResponseEntity<String> getPdfForIncome(@RequestBody User user) {
        referenceProducer.produce(user, "reference.income");
        return ResponseEntity.ok("Income reference PDF for " + user.getLastName() + " created");
    }

    @PostMapping("/reference/medical")
    public ResponseEntity<String> getPdfForMedical(@RequestBody User user) {
        referenceProducer.produce(user, "reference.medical");
        return ResponseEntity.ok("Medical reference PDF for " + user.getLastName() + " created");
    }
}
