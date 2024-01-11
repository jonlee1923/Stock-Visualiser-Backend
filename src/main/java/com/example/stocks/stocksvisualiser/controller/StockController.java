package com.example.stocks.stocksvisualiser.controller;

import com.example.stocks.stocksvisualiser.dto.response.AggregateResponse;
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

    @GetMapping("/aggregate")
    public ResponseEntity<List<AggregateResponse>> getAggregate(
            @RequestParam String symbol,
            @RequestParam Timespan timespan,
            @RequestParam LocalDate from,
            @RequestParam LocalDate to
    ) {
            return stockService.getAggregate(symbol, timespan, from, to);
    }

    @GetMapping("/ticker")
    public ResponseEntity<List<TickerResponse>> getTicker(
            @RequestParam String symbol
    ) {
        return stockService.getTicker(symbol);
    }

}
