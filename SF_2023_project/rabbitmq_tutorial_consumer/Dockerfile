FROM openjdk:17

ADD target/rabbitmq_tutorial_consumer-0.0.1.jar rabbitmq_tutorial_consumer-0.0.1.jar

ENTRYPOINT ["java", "-jar", "rabbitmq_tutorial_consumer-0.0.1.jar"]

EXPOSE 8080