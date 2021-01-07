package ru.itis.rabbitmqpractice.producers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.stereotype.Component;
import ru.itis.rabbitmqpractice.models.User;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Component
public class GoodWorkerProducer {
    private final static String EXCHANGE_NAME = "good_worker_exchange";
    private final static String EXCHANGE_TYPE = "fanout";
    private final static String QUEUE_1 = "end_work_month_q";
    private final static String QUEUE_2 = "salary_request_q";


    private final ConnectionFactory connectionFactory;
    private final ObjectMapper objectMapper;
    private Channel channel;

    public GoodWorkerProducer(ConnectionFactory connectionFactory, ObjectMapper objectMapper) {
        this.connectionFactory = connectionFactory;
        this.objectMapper = objectMapper;
        try {
            Connection connection = connectionFactory.newConnection();
            channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);
            channel.queueBind(QUEUE_1, EXCHANGE_NAME, "");
            channel.queueBind(QUEUE_2, EXCHANGE_NAME, "");
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void produce(User user) {
        try {
            String message = objectMapper.writeValueAsString(user);
            channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
