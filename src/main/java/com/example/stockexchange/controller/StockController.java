package com.example.stockexchange.controller;

import com.example.stockexchange.model.Stock;
import com.example.stockexchange.model.StockResponse;
import com.example.stockexchange.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockController {

    private static final Logger logger = LoggerFactory.getLogger(StockController.class);
    @Autowired
    private StockService stockService;

    @GetMapping(value = "/price")
    public StockResponse getPrice() {
        StockResponse stockResponse = new StockResponse();
        Stock stock = stockService.getStockPrice();
        if (null != stock && !StringUtils.isEmpty(stock.getPreviousPrice())) {
            double mid = Double.parseDouble(stock.getAskPrice()) + Double.parseDouble(stock.getBidPrice()) / 2;
            stockResponse.setSymbol(stock.getSymbol());
            stockResponse.setPrice(String.valueOf(mid));
            stockResponse.setTrend(Double.parseDouble(stock.getPreviousPrice()) < Double.parseDouble(stock.getBidPrice()) ? "Up" : "Down");
            logger.info(" stock information is sent");
        } else if (null != stock && StringUtils.isEmpty(stock.getPreviousPrice())) {
            double mid = (Double.parseDouble(stock.getAskPrice()) + Double.parseDouble(stock.getBidPrice())) / 2;
            stockResponse.setSymbol(stock.getSymbol());
            stockResponse.setPrice(String.valueOf(mid));
            stockResponse.setTrend("Up");
        }
        return stockResponse;
    }

}
