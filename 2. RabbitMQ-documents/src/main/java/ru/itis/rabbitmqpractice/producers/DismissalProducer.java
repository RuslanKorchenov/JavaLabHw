package ru.itis.rabbitmqpractice.producers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.rabbitmqpractice.models.User;
import ru.itis.rabbitmqpractice.utils.ChannelCreator;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Component
public class DismissalProducer {

    private final static String EXCHANGE_NAME = "dismissal_exchange";
    private final static String EXCHANGE_TYPE = "direct";
    private final static String BINDING_KEY = "dismissal";
    private final static String QUEUE = "dismissal_q";
    private final ConnectionFactory connectionFactory;
    private final ObjectMapper objectMapper;
    private Channel channel;

    public DismissalProducer(ConnectionFactory connectionFactory, ObjectMapper objectMapper) {
        this.connectionFactory = connectionFactory;
        this.objectMapper = objectMapper;
        try {
            Connection connection = connectionFactory.newConnection();
            channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);
            channel.queueBind(QUEUE, EXCHANGE_NAME, BINDING_KEY);
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void produce(User user, String routingKey) {
        try {
            String message = objectMapper.writeValueAsString(user);
            channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes());
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
