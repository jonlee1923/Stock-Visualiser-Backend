package com.example.stocks.stocksvisualiser.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import yahoofinance.YahooFinance;

import java.io.IOException;

@AllArgsConstructor
@Service
public class StockService {
    public Stock findStock(final String ticker){
        try{
            return new Stock(YahooFinance.get(ticker));
        } catch(IOException e){
            System.out.println(e);
        }
        return null;
    }
}
