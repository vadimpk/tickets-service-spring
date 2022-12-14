package com.naukma.pricemanager;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class PriceManager {

    private final PriceManagerConfig config;

    public PriceManager(PriceManagerConfig config) {
        this.config = config;
    }

//    public int setPrice(Run run) {
//        return run.getRoute().getDistance() * config.getProperties().getDefaultRate();
//    }

    public double setPrice (int distance) {
        return distance * config.getProperties().getDefaultRate();
    }

    public double convertPriceTo(String currency, double price) {
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = String.format("https://anyapi.io/api/v1/exchange/convert?apiKey=odpt6s4s81g517dbm10oea8saesgbl28n3l4v19osk6mo7tvsd8&base=USD&to=%s&amount=%f", currency, price);
        RequestEntity<Void> request = RequestEntity.get(resourceUrl)
                .accept(MediaType.APPLICATION_JSON).build();

        try {
            ParameterizedTypeReference<HashMap<String, String>> responseType = new ParameterizedTypeReference<>() {
            };
            Map<String, String> jsonDictionary = restTemplate.exchange(request, responseType).getBody();
            if (jsonDictionary.get("converted") == null) return -1;
            return Double.parseDouble(jsonDictionary.get("converted"));
        } catch (HttpClientErrorException e){
            throw new NoSuchCurrencyException(e);
        }
    }
}
