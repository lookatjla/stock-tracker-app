package org.example;

import org.example.utilities.AlphaVantageService;
import org.example.utilities.StockAnalyzer;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            AlphaVantageService alphaVantageService = new AlphaVantageService();

            // Get daily closing prices for the last 10 years
            Map<LocalDate, BigDecimal> dailyClosingPrices = alphaVantageService.getDailyClosingPrices("TSLA", "Daily");

            // Assuming the most recent price is today's closing price
            BigDecimal currentPrice = dailyClosingPrices.get(LocalDate.now());

            // Create an instance of StockAnalyzer with the data
            StockAnalyzer analyzer = new StockAnalyzer(dailyClosingPrices, currentPrice);

            // Define start and end dates for the range
            LocalDate startDate = LocalDate.of(2024,  10, 1);
            LocalDate endDate = LocalDate.of(2024, 10, 31);

            // Run analysis and print summary for the date range
            analyzer.printAnalysisSummary(startDate, endDate);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}