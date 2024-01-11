package com.example.stocks.stocksvisualiser.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AggregateResponse {
    @JsonProperty("o")
    private double open;
    @JsonProperty("c")
    private double close;
    @JsonProperty("h")
    private double high;
    @JsonProperty("l")
    private double low;
    @JsonProperty("n")
    private int numberOfTx;
    @JsonProperty("t")
    private Long startTime;
    @JsonProperty("v")
    private double volume;
    @JsonProperty("vw")
    private double vwap;
}
