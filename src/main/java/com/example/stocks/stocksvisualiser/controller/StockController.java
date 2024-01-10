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
import java.util.Date;
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
            @RequestParam String symbol,
            @RequestParam Timespan timespan,
            @RequestParam LocalDate from,
            @RequestParam LocalDate to
    ) {
        try {

            return stockService.getTicker(symbol, timespan, from, to);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return stockService.getTicker(symbol, timespan, from, to);

        }
    }

    @GetMapping("/test")
    public void test(@RequestParam String test) {
        System.out.println(test);
    }
}
