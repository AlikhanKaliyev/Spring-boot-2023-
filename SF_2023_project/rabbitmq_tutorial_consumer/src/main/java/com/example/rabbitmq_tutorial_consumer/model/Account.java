package com.example.rabbitmq_tutorial_consumer.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Getter
@Setter
@ToString
public class Account {

    private String username;

    private String password;

    private String email;

    private LocalDateTime created_on;
    private ZonedDateTime last_login;
}
