package com.example.stocks.stocksvisualiser.service;

import com.example.stocks.stocksvisualiser.enums.Timespan;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class StockServiceTest {
    @Autowired
    private StockService stockService;

    @Test
    void invoke(){
        stockService.getTicker("AAPL", Timespan.DAY, LocalDate.now().minusDays(3), LocalDate.now().minusDays(2));
    }
}
