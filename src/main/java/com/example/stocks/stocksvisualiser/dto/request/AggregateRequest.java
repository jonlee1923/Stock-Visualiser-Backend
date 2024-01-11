package com.example.stocks.stocksvisualiser.dto.request;

import com.example.stocks.stocksvisualiser.enums.Timespan;

import java.time.LocalDate;
public record AggregateRequest(String symbol, Timespan timespan, LocalDate from, LocalDate to) {
}
