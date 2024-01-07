package com.example.stocks.stocksvisualiser.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TickerResponse {
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
    private long startTime;
    @JsonProperty("v")
    private double volume;
    @JsonProperty("vw")
    private double vwap;
}
