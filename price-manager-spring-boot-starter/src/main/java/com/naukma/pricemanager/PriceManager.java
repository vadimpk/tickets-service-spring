package com.naukma.pricemanager;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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

    public double covertPriceToUSD(int price) {

        try {
            URL url = new URL(String.format("https://anyapi.io/api/v1/exchange/convert?apiKey=odpt6s4s81g517dbm10oea8saesgbl28n3l4v19osk6mo7tvsd8&base=EUR&to=USD&amount=%d", price));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Getting the response code
            int responsecode = conn.getResponseCode();

            if (responsecode != 200) {
                return -1;
            }

            else {
                StringBuilder inline = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                //Write all the JSON data into a string using a scanner
                while (scanner.hasNext()) {
                    inline.append(scanner.nextLine());
                }

                //Close the scanner
                scanner.close();

                //Using the JSON simple library parse the string into a json object
                JSONParser parse = new JSONParser();
                JSONObject data_obj = (JSONObject) parse.parse(inline.toString());

                //Get the required object from the above created object
                return (int) (double) data_obj.get("converted");
            }

        } catch (IOException | ParseException e) {
            System.out.println(e);
            return -1;
        }
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
