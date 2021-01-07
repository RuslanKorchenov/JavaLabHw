package ru.itis.hateoas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.w3c.dom.Attr;
import ru.itis.hateoas.models.Attraction;
import ru.itis.hateoas.models.Park;
import ru.itis.hateoas.repositories.AttractionRepository;
import ru.itis.hateoas.repositories.ParkRepository;

import java.util.Date;

@SpringBootApplication
public class HateoasApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(HateoasApplication.class, args);
//
//        ParkRepository parkRepository = context.getBean(ParkRepository.class);
//        AttractionRepository attractionRepository = context.getBean(AttractionRepository.class);
//
//        Park park = Park.builder()
//                .address("Кремлевская 35")
//                .name("Цирк")
//                .rank(10).build();
//
//        Attraction attraction = Attraction.builder()
//                .countOfSeats(100)
//                .status(Attraction.Status.AVAILABLE)
//                .park(park)
//                .build();
//        Park park = Park.builder()
//                .address("Баумана")
//                .name("Зоопарк")
//                .rank(10).build();
//
//        Attraction attraction = Attraction.builder()
//                .name("Покорми льва")
//                .countOfSeats(100)
//                .status(Attraction.Status.AVAILABLE)
//                .park(park)
//                .build();
//        parkRepository.save(park);
//        attractionRepository.save(attraction);
    }

}
