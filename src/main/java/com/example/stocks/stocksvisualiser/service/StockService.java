package com.example.stocks.stocksvisualiser.service;

import com.example.stocks.stocksvisualiser.dto.response.AggregateResponse;
import com.example.stocks.stocksvisualiser.dto.response.TickerResponse;
import com.example.stocks.stocksvisualiser.enums.Timespan;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface StockService {
    ResponseEntity<List<TickerResponse>> getTicker(String symbol);
    ResponseEntity<List<AggregateResponse>> getAggregate(String symbol, Timespan timespan, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate from, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate to);
}
