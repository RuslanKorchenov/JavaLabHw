package consumers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import dto.User;
import services.PdfService;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


public class DismissalConsumer {
    private final static String EXCHANGE_NAME = "documents";

    private final static String QUEUE_NAME = "dismissal";
    private final static String FILEPATH = "C:\\Users\\rusel\\IdeaProjects\\RabbitMQ\\pdfs\\";

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        final ObjectMapper mapper = new ObjectMapper();
        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "");
            channel.basicQos(3);

            channel.basicConsume(QUEUE_NAME, false, (consumerTag, message) -> {
                        try {
                            User user = mapper.readValue(message.getBody(), User.class);
                            System.out.println("DOING " + user.toString());
                            PdfService.createPdf(FILEPATH, user, "Dismissal");
                            channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
                            System.out.println("COMPLETED");
                        } catch (Exception e) {
                            System.out.println("FAILED");
                            channel.basicReject(message.getEnvelope().getDeliveryTag(), false);
                        }
                    },
                    consumerTag -> {
                    });

        } catch (IOException | TimeoutException e) {
            throw new IllegalStateException(e);
        }
    }
}
