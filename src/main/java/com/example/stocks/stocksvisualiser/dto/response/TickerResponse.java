package com.example.stocks.stocksvisualiser.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TickerResponse {
    String ticker;
    String name;
    String market;
    String locale;
    String primary_exchange;
    String type;
    Boolean active;
    String currency_name;
    String cik;
    String composite_figi;
    String share_class_figi;
    String last_updated_utc;
}
