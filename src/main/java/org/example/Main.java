package org.example;

import org.example.domain.StockModel;
import org.example.utilities.AlphaVantageService;

public class Main {
    public static void main(String[] args) {
        AlphaVantageService alphaVantageService = new AlphaVantageService();
//        StockModel stockModel = new StockModel("AAPL", "30min");

        try {
            System.out.println(alphaVantageService.getStockData("AAPL", "1min"));
//            System.out.println("Symbol: " + stockModel.getSymbol());
//            System.out.println("Price: " + stockModel.getCurrentPrice());
        } catch (Exception e) {
            System.out.println("ERROR");
        }
    }
}