package com.example.stocks.stocksvisualiser.service;

import com.example.stocks.stocksvisualiser.dto.response.AggregateResponse;
import com.example.stocks.stocksvisualiser.dto.response.StockApiResponse;
import com.example.stocks.stocksvisualiser.dto.response.TickerResponse;
import com.example.stocks.stocksvisualiser.enums.Timespan;
import com.example.stocks.stocksvisualiser.service.impl.StockServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class StockServiceImplTest {
    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private StockServiceImpl stockService;

    @BeforeEach
    void setUp() {

    }

    @Test
    void testGetAggregate() {
        // Arrange
        String symbol = "AAPL";
        Timespan timespan = Timespan.DAY;
        LocalDate from = LocalDate.now().minusDays(2);
        LocalDate to = LocalDate.now();

        // Mock the restTemplate response
        AggregateResponse ticker1 = new AggregateResponse(187.35, 185.65, 188.45, 183.895, 1008872, 1704171600000L, 8.1964874E6, 185.9455);
        AggregateResponse ticker2 = new AggregateResponse(187.15, 185.64, 188.44, 183.885, 1008871, 1704171600010L, 8.1964874E7, 185.9465);
        List<AggregateResponse> tickerList = Arrays.asList(ticker1, ticker2);
        StockApiResponse<AggregateResponse> stockApiResponse = new StockApiResponse<>();
        stockApiResponse.setResults(tickerList);
        ResponseEntity<StockApiResponse<AggregateResponse>> mockResponseEntity = ResponseEntity.ok(stockApiResponse);
        when(restTemplate.exchange(any(String.class), any(HttpMethod.class), any(), any(ParameterizedTypeReference.class)))
                .thenReturn(mockResponseEntity);

        // Act
        ResponseEntity<List<AggregateResponse>> responseEntity = stockService.getAggregate(symbol, timespan, from, to);

        // Assert
        verify(restTemplate, times(1)).exchange(any(String.class), any(HttpMethod.class), any(), any(ParameterizedTypeReference.class));
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(tickerList, responseEntity.getBody());
    }

    @Test
    void testGetTicker() {
        // Arrange
        String symbol = "AAPL";

        // Mock the restTemplate response
        TickerResponse tickerDetails = new TickerResponse(
                "AAPL",
                "Apple Inc.",
                "stocks",
                "us",
                "XNAS",
                "CS",
                true,
                "usd",
                "0000320193",
                "BBG000B9XRY4",
                "BBG001S5N8V8",
                "2024-01-10T00:00:00Z"
        );
        List<TickerResponse> response = Arrays.asList(tickerDetails);
        StockApiResponse<TickerResponse> stockApiResponse = new StockApiResponse<>();
        stockApiResponse.setResults(response);
        ResponseEntity<StockApiResponse<TickerResponse>> mockResponseEntity = ResponseEntity.ok(stockApiResponse);
        when(restTemplate.exchange(any(String.class), any(HttpMethod.class), any(), any(ParameterizedTypeReference.class)))
                .thenReturn(mockResponseEntity);

        // Act
        ResponseEntity<List<TickerResponse>> responseEntity = stockService.getTicker(symbol);

        // Assert
        verify(restTemplate, times(1)).exchange(any(String.class), any(HttpMethod.class), any(), any(ParameterizedTypeReference.class));
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(response, responseEntity.getBody());
    }
}
