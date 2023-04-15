package com.example.consumingweatherapplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.example.consumingwebservice.wsdl.GetCityRequest;
import com.example.consumingwebservice.wsdl.GetCityResponse;

public class CityClient extends WebServiceGatewaySupport{
    private static final Logger log = LoggerFactory.getLogger(CityClient.class);

    public GetCityResponse getCity(String city) {

        GetCityRequest request = new GetCityRequest();
        request.setName(city);

        log.info("Requesting location for " + city);

        GetCityResponse response = (GetCityResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws/cities", request,
                        new SoapActionCallback(
                                "http://spring.io/guides/gs-producing-web-service/GetCityRequest"));

        return response;
    }
}
