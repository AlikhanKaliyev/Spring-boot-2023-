package kz.kbtu.sfcourse.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {
    public static final String ROUTING_APPLICATION = "routing.application.*";
    public static final String ROUTING_USER = "routing.user.*";
    public static final String ROUTING_ALL = "routing.#";

    @Bean
    Queue queueApplication() { return new Queue("queue.Application",false); }
    @Bean
    Queue queueUser() { return new Queue("queue.User",false); }
    @Bean
    Queue queueAll() { return new Queue("queue.All",false); }

    @Bean
    TopicExchange exchange() { return new TopicExchange("exchange.topic"); }
    @Bean
    Binding bindingApplication(Queue queueApplication, TopicExchange exchange) {
        return BindingBuilder.bind(queueApplication).to(exchange).with(ROUTING_APPLICATION);
    }
    @Bean
    Binding bindingUser(Queue queueUser, TopicExchange exchange) {
        return BindingBuilder.bind(queueUser).to(exchange).with(ROUTING_USER);
    }
    @Bean
    Binding bindingAll(Queue queueAll, TopicExchange exchange) {
        return BindingBuilder.bind(queueAll).to(exchange).with(ROUTING_ALL);
    }
    @Bean
    MessageConverter messageConverter(ObjectMapper mapper) { return new Jackson2JsonMessageConverter(mapper); }

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory factory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        rabbitTemplate.setMessageConverter(messageConverter(mapper));
        return rabbitTemplate;
    }
}
