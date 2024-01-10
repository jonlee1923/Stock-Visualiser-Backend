package com.example.stocks.stocksvisualiser.service.impl;


import com.example.stocks.stocksvisualiser.dto.response.ApiResponse;
import com.example.stocks.stocksvisualiser.dto.response.TickerResponse;
import com.example.stocks.stocksvisualiser.enums.Timespan;
import com.example.stocks.stocksvisualiser.service.StockService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {
    @Autowired
    private final RestTemplate restTemplate;

    private static Logger logger = LoggerFactory.getLogger(StockServiceImpl.class);

    @Value("${stockAPI.aggregates.baseUrl}")
    String stockAggregateApiUrl;

    @Value("${stockAPI.apiKey}")
    String apiKey;

    public ResponseEntity<List<TickerResponse>> getTicker(String symbol, Timespan timespan, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate from, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate to) {
        String url = stockAggregateApiUrl + "aggs/ticker/" + symbol + "/range/1/" + timespan.name().toLowerCase() + "/" + from.toString() + "/" + to.toString() + "?adjusted=true&sort=asc&limit=120&apiKey=" + apiKey;
        logger.info("Url: "+url);
        try {
            ParameterizedTypeReference<ApiResponse<TickerResponse>> responseType = new ParameterizedTypeReference<ApiResponse<TickerResponse>>() {};
            ResponseEntity<ApiResponse<TickerResponse>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, responseType);
            ApiResponse<TickerResponse> data = responseEntity.getBody();
            return new ResponseEntity(data.getResults(), HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException("Error calling external API: ", e);
        }
    }
}
