package org.example.utilities;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

public class StockAnalyzer {
    private Map<LocalDate, BigDecimal> dailyClosingPrices;  // Map of dates to daily closing prices
    private LocalDate startDate;                            // Start date of the analysis period (10 years ago)
    private LocalDate endDate;                              // End date of the analysis period (today or recent date)
    private BigDecimal currentStockPrice;                   // Current stock price

    // Constructor
    public StockAnalyzer(Map<LocalDate, BigDecimal> dailyClosingPrices, BigDecimal currentStockPrice) {
        this.dailyClosingPrices = dailyClosingPrices;
        this.currentStockPrice = currentStockPrice;
        this.endDate = LocalDate.now();
        this.startDate = endDate.minusYears(10);  // 10 years before the end date
    }

    // Getter and Setter methods
    public Map<LocalDate, BigDecimal> getDailyClosingPrices() {
        return dailyClosingPrices;
    }

    public void setDailyClosingPrices(Map<LocalDate, BigDecimal> dailyClosingPrices) {
        this.dailyClosingPrices = dailyClosingPrices;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getCurrentStockPrice() {
        return currentStockPrice;
    }

    public void setCurrentStockPrice(BigDecimal currentStockPrice) {
        this.currentStockPrice = currentStockPrice;
    }

    // Method to calculate total percentage change over the 10-year period
    public Optional<BigDecimal> calculateTotalPercentageChange() {
        if (dailyClosingPrices == null || dailyClosingPrices.isEmpty()) {
            return Optional.empty();
        }

        BigDecimal startPrice = dailyClosingPrices.get(startDate);
        BigDecimal endPrice = dailyClosingPrices.get(endDate);

        if (startPrice == null || endPrice == null) {
            return Optional.empty();
        }

        return Optional.of(
                endPrice.subtract(startPrice)
                        .divide(startPrice, RoundingMode.HALF_UP)
                        .multiply(BigDecimal.valueOf(100))
        );
    }

    // Method to calculate average annual percentage change
    public Optional<BigDecimal> calculateAverageAnnualChange() {
        Optional<BigDecimal> totalPercentageChange = calculateTotalPercentageChange();
        long years = startDate.until(endDate).getYears();

        if (years == 0 || totalPercentageChange.isEmpty()) return Optional.empty();

        return Optional.of(totalPercentageChange.get().divide(BigDecimal.valueOf(years), RoundingMode.HALF_UP));
    }

    public void printAnalysisSummary(LocalDate userStartDate, LocalDate userEndDate) {
        // Validate the provided dates
        if (userStartDate == null || userEndDate == null || userStartDate.isAfter(userEndDate)) {
            System.out.println("Invalid date range. Please ensure that start date is before end date.");
            return;
        }

        System.out.println("Stock Analysis Summary:");
        System.out.println("Start Date: " + userStartDate);
        System.out.println("End Date: " + userEndDate);
        System.out.println("Daily Closing Prices in the Specified Range:");

        // Print daily closing prices within the date range
        dailyClosingPrices.entrySet().stream()
                .filter(entry -> !entry.getKey().isBefore(userStartDate) && !entry.getKey().isAfter(userEndDate))
                .forEach(entry -> System.out.println("Date: " + entry.getKey() + ", Closing Price: " + entry.getValue()));

        // Calculate and display total and average annual percentage changes
        Optional<BigDecimal> percentageChange = calculatePercentageChange(userStartDate, userEndDate);
        System.out.println("Total Percentage Change: " + percentageChange.orElse(BigDecimal.ZERO) + "%");

        Optional<BigDecimal> averageAnnualChange = calculateAverageAnnualChange(userStartDate, userEndDate);
        System.out.println("Average Annual Change: " + averageAnnualChange.orElse(BigDecimal.ZERO) + "%");
    }

    // Helper method to calculate percentage change over a custom date range
    private Optional<BigDecimal> calculatePercentageChange(LocalDate startDate, LocalDate endDate) {
        BigDecimal startPrice = dailyClosingPrices.get(startDate);
        BigDecimal endPrice = dailyClosingPrices.get(endDate);

        if (startPrice == null || endPrice == null) {
            return Optional.empty();
        }

        return Optional.of(
                endPrice.subtract(startPrice)
                        .divide(startPrice, RoundingMode.HALF_UP)
                        .multiply(BigDecimal.valueOf(100))
        );
    }

    // Helper method to calculate average annual change over a custom date range
    private Optional<BigDecimal> calculateAverageAnnualChange(LocalDate startDate, LocalDate endDate) {
        Optional<BigDecimal> percentageChange = calculatePercentageChange(startDate, endDate);
        long years = startDate.until(endDate).getYears();

        if (years == 0 || percentageChange.isEmpty()) return Optional.empty();

        return Optional.of(percentageChange.get().divide(BigDecimal.valueOf(years), RoundingMode.HALF_UP));
    }
}