package com.example.stocks.stocksvisualiser.controller;

import com.example.stocks.stocksvisualiser.dto.response.TickerResponse;
import com.example.stocks.stocksvisualiser.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockController {
    @Autowired
    StockService stockService;

//    @GetMapping("/ticker")
//    public TickerResponse getTicker(){
////        return stockService.getTicker("AAPL");
//    }
}
