package com.example.rabbitmq_tutorial_consumer.controller;

import com.example.rabbitmq_tutorial_consumer.model.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Slf4j
public class Consumer{

    @RabbitListener(queues = "queue.User")
    private void receiveFromUser(List<Account> accounts) {
        log.info("Message received from QUEUE USER->{}", accounts);
    }
    @RabbitListener(queues = "queue.Application")
    private void receiveFromApplication(List<Account> accounts ) {
        log.info("Message received from QUEUE APPLICATION->{}", accounts);
    }
    @RabbitListener(queues = "queue.All")
    private void receiveFromAll(List<Account> accounts) {
        log.info("Message received from QUEUE ALL->{}", accounts);
    }

}
