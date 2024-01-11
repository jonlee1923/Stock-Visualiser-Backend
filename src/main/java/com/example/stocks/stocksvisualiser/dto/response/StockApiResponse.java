package com.example.stocks.stocksvisualiser.dto.response;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StockApiResponse<T> extends ApiResponse{
    private String count;
    private List<T> results;
}
