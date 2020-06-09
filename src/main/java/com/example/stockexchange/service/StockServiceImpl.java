package com.example.stockexchange.service;

import com.example.stockexchange.model.Stock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl implements StockService {

    private static final Logger logger = LoggerFactory.getLogger(StockServiceImpl.class);

    @Override
    public Stock getStockPrice() {
        Stock stock = new Stock();
        stock.setAskPrice("20.1");
        stock.setBidPrice("20.4");
        stock.setPreviousPrice("21.1");
        stock.setSymbol("D05:SGX");
        logger.info("Stock infomation from Reutor is sent");
        return stock;
    }
}
