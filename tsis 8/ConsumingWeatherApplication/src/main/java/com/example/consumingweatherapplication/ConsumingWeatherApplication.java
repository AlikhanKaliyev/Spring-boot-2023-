package com.example.consumingweatherapplication;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.consumingwebservice.wsdl.GetCityResponse;

@SpringBootApplication
public class ConsumingWeatherApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumingWeatherApplication.class, args);
    }
    @Bean
    CommandLineRunner lookup(CityClient quoteClient) {
        return args -> {
            String city = "Almaty";

            if (args.length > 0) {
                city = args[0];
            }
            GetCityResponse response = quoteClient.getCity(city);
            System.err.println(response.getCity().getPopulation());
        };
    }

}
