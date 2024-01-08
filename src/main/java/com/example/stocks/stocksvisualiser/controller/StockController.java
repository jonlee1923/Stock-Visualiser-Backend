package com.example.stocks.stocksvisualiser.controller;

import com.example.stocks.stocksvisualiser.dto.request.TickerRequest;
import com.example.stocks.stocksvisualiser.dto.response.TickerResponse;
import com.example.stocks.stocksvisualiser.enums.Timespan;
import com.example.stocks.stocksvisualiser.service.impl.StockServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/stocks")
public class StockController {
    @Autowired
    StockServiceImpl stockService;

    @Value("${stockAPI.aggregates.baseUrl}")
    String testing;

    @Value("${stockAPI.apiKey}")
    private String apiKey;

    @GetMapping("/ticker")
    public ResponseEntity<List<TickerResponse>> getTicker(
            @RequestBody TickerRequest tickerRequest
    ) {
        try {

            return stockService.getTicker(tickerRequest.symbol(), tickerRequest.timespan(), tickerRequest.from(), tickerRequest.to());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return stockService.getTicker(tickerRequest.symbol(), tickerRequest.timespan(), tickerRequest.from(), tickerRequest.to());

        }
    }

    @GetMapping("/test")
    public void test(@RequestParam String test) {
        System.out.println(test);
    }
}
