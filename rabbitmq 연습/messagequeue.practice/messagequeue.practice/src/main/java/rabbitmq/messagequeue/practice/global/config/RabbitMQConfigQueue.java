package rabbitmq.messagequeue.practice.global.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQConfigQueue {

    @Value("${server.name}")
    private String serverName;

    // 저장 큐
    @Bean
    public Queue storageQueue() {
        return QueueBuilder.durable("chat.storage.queue")
                .withArgument("x-queue-mode", "lazy")
                .build();
    }
}
