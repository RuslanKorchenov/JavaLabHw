import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import dto.User;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class Producer {
    private final static String EXCHANGE_NAME = "documents";
    private final static String EXCHANGE_TYPE = "fanout";

    private final static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);

            while (true) {
                User user = createUser();
                channel.basicPublish(EXCHANGE_NAME, "", null, mapper.writeValueAsBytes(user));
            }
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static User createUser() {
        Scanner sc = new Scanner(System.in);
        User user = User.builder().build();
        System.out.println("Введите имя:");
        user.setFirstName(sc.nextLine());
        System.out.println("Введите фамилию:");
        user.setLastName(sc.nextLine());
        System.out.println("Введите номер паспорта:");
        user.setPasswordNumber(sc.nextLine());
        System.out.println("Введите дату выдачи паспорта:");
        user.setDate(sc.nextLine());
        System.out.println("Введите возраст:");
        user.setAge(sc.nextInt());
        return user;
    }
}
