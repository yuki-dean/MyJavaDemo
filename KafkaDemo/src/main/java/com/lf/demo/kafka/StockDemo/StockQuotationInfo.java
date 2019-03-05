package com.lf.demo.kafka.StockDemo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class StockQuotationInfo implements Serializable {
    private static final  long serialVersionUID = 1L;
    private String stockCode;
    private String stockName;
    private long tradeTime;
    private float preClosePrice;
    private float openPrice;
    private float currentPrice;
    private float highPrice;
    private float lowPrice;
}
