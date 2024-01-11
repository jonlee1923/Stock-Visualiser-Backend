package com.example.stocks.stocksvisualiser.service.impl;


import com.example.stocks.stocksvisualiser.dto.response.AggregateResponse;
import com.example.stocks.stocksvisualiser.dto.response.StockApiResponse;
import com.example.stocks.stocksvisualiser.dto.response.TickerResponse;
import com.example.stocks.stocksvisualiser.enums.Timespan;
import com.example.stocks.stocksvisualiser.service.StockService;
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

    @Value("${stockAPI.baseUrl}")
    String stockBaseUrl;

    @Value("${stockAPI.tickers.endpoint}")
    String stockTickerEndpoint;

    @Value("${stockAPI.aggregates.endpoint}")
    String stockAggEndpoint;
    @Value("${stockAPI.apiKey}")
    String apiKey;

    public ResponseEntity<List<TickerResponse>> getTicker(String symbol){
        String url = stockBaseUrl + stockTickerEndpoint + symbol + "&active=true&apiKey=" + apiKey;
        logger.info("Making rest call to fetch details of ticker: "+symbol);
        logger.info("URL" + url);
        logger.error("url" + url);
        try {
            ParameterizedTypeReference<StockApiResponse<TickerResponse>> responseType = new ParameterizedTypeReference<StockApiResponse<TickerResponse>>() {};
            ResponseEntity<StockApiResponse<TickerResponse>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, responseType);
            StockApiResponse<TickerResponse> data = responseEntity.getBody();
            return new ResponseEntity(data.getResults(), HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException("Error calling external API: ", e);
        }
    }
    public ResponseEntity<List<AggregateResponse>> getAggregate(String symbol, Timespan timespan, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate from, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate to) {
        String url = stockBaseUrl + stockAggEndpoint + symbol + "/range/1/" + timespan.name().toLowerCase() + "/" + from.toString() + "/" + to.toString() + "?adjusted=true&sort=asc&limit=120&apiKey=" + apiKey;
        logger.info("Making rest call to fetch aggregate data for ticker: "+symbol);
        try {
            ParameterizedTypeReference<StockApiResponse<AggregateResponse>> responseType = new ParameterizedTypeReference<StockApiResponse<AggregateResponse>>() {};
            ResponseEntity<StockApiResponse<AggregateResponse>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, responseType);
            StockApiResponse<AggregateResponse> data = responseEntity.getBody();
            return new ResponseEntity(data.getResults(), HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException("Error calling external API: ", e);
        }
    }
}
