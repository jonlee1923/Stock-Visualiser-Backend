package com.example.stocks.stocksvisualiser.service;


import com.example.stocks.stocksvisualiser.dto.response.ApiResponse;
import com.example.stocks.stocksvisualiser.dto.response.TickerResponse;
import com.example.stocks.stocksvisualiser.enums.Timespan;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Service
public class StockService {

    @Autowired
    private final RestTemplate restTemplate;

//    @Value("${stockAPI.aggregates.baseUrl:https://api.polygon.io/v2/}")
//    private String stockAggregateApiUrl;
//
//    @Value("${stockAPI.apiKey:}")
//    private String apiKey;

    public void getTicker(String symbol, Timespan timespan, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate from, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate to){
//        String url = stockAggregateApiUrl + "aggs/ticker/" + symbol + "/range/1/" + timespan + "/" + from.toString() + "/" + to.toString() + "?adjusted=true&sort=asc&limit=120&apiKey=" + apiKey;
        String url = "https://api.polygon.io/v2/" + "aggs/ticker/" + symbol + "/range/1/" + timespan.name().toLowerCase() + "/" + from.toString() + "/" + to.toString() + "?adjusted=true&sort=asc&limit=120&apiKey=" + "Lpi707UDz4sOpvBMSRhmKmcCCpRfJqNr";
        System.out.println(url);
        ParameterizedTypeReference<ApiResponse<TickerResponse>> responseType = new ParameterizedTypeReference<ApiResponse<TickerResponse>>() {};
        ResponseEntity<ApiResponse<TickerResponse>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, responseType);
        ApiResponse<TickerResponse> data = responseEntity.getBody();
        System.out.println(data);
    }
}
