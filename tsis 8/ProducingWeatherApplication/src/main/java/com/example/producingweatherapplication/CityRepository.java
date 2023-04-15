package com.example.producingweatherapplication;

import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

import io.spring.guides.gs_producing_web_service.City;
import io.spring.guides.gs_producing_web_service.Weather;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class CityRepository {
    private static final Map<String, City> cities = new HashMap<>();

    @PostConstruct
    public void initData() {
        City Almaty = new City();
        Almaty.setName("Almaty");
        Almaty.setWeather(Weather.SUNNY);
        cities.put(Almaty.getName(),Almaty);

        City Astana = new City();
        Astana.setName("Astana");
        Astana.setWeather(Weather.CLOUDY);
        cities.put(Astana.getName(),Astana);

        City Aktobe = new City();
        Aktobe.setName("Aktobe");
        Aktobe.setWeather(Weather.SNOWY);
        cities.put(Aktobe.getName(),Aktobe);
    }

    public City findCity(String name) {
        Assert.notNull(name, "The city's name mustn't be null");
        return cities.get(name);
    }
}
