package com.example.stocks.stocksvisualiser.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.With;
import yahoofinance.Stock;

import java.time.LocalDateTime;
@Getter
@With
@AllArgsConstructor
public class StockDTO {
    private final Stock stock;
    private final LocalDateTime lastAccessed;

    public StockDTO(final Stock stock){
        this.stock = stock;
        lastAccessed = LocalDateTime.now();
    }
}
