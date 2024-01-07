package com.example.stocks.stocksvisualiser.dto.response;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ApiResponse<T>{
    private String count;
    private String status;
    private List<T> results;
}
