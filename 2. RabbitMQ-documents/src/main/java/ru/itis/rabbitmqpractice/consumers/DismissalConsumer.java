package ru.itis.rabbitmqpractice.consumers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import ru.itis.rabbitmqpractice.models.User;
import ru.itis.rabbitmqpractice.utils.ChannelCreator;
import ru.itis.rabbitmqpractice.utils.PdfCreator;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.TimeoutException;

@Component
public class DismissalConsumer {

    private final String QUEUE = "dismissal_q";
    private ObjectMapper objectMapper;
    private PdfCreator pdfCreator;
    private ConnectionFactory connectionFactory;

    public DismissalConsumer(ObjectMapper objectMapper, ConnectionFactory connectionFactory, PdfCreator pdfCreator) {
        this.objectMapper = objectMapper;
        this.pdfCreator = pdfCreator;
        this.connectionFactory = connectionFactory;
        consume();
    }

    public void consume() {
        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.basicQos(3);
            channel.queueDeclare(QUEUE, true, false, true, null);
            DeliverCallback deliverCallback = (consumerTag, message) -> {
                User user = objectMapper.readValue(message.getBody(), User.class);
                System.out.println("DOING " + user.getFirstName());

                pdfCreator.createPdf(user, "Dismissal");
                System.out.println("Created dismissal document for " + user.getLastName());
                channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
            };
            channel.basicConsume(QUEUE, false, deliverCallback, (consumerTag, message) -> {
            });
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }

    }
}
