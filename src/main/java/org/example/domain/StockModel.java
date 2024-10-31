package org.example.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;

public class StockModel {
    private String symbol;
    private String companyName;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal currentPrice;
    private Map<LocalDate, BigDecimal> historicalPrices;
    private String interval;

    public StockModel(String symbol, String interval) {
        this.symbol = symbol;
        this.interval = interval;
    }

    public StockModel(String symbol, String companyName, BigDecimal currentPrice) {
        this.symbol = symbol;
        this.companyName = companyName;
        this.currentPrice = currentPrice;
    }

    public StockModel(String symbol, String companyName, LocalDate startDate, LocalDate endDate, BigDecimal currentPrice, Map<LocalDate, BigDecimal> historicalPrices) {
        this.symbol = symbol;
        this.companyName = companyName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.currentPrice = currentPrice;
        this.historicalPrices = historicalPrices;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Map<LocalDate, BigDecimal> getHistoricalPrices() {
        return historicalPrices;
    }

    public void setHistoricalPrices(Map<LocalDate, BigDecimal> historicalPrices) {
        this.historicalPrices = historicalPrices;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    @Override
    public String toString() {
        return "StockModel{" +
                "symbol='" + symbol + '\'' +
                ", companyName='" + companyName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", currentPrice=" + currentPrice +
                ", historicalPrices=" + historicalPrices +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockModel that = (StockModel) o;
        return Objects.equals(symbol, that.symbol) && Objects.equals(companyName, that.companyName) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate) && Objects.equals(currentPrice, that.currentPrice) && Objects.equals(historicalPrices, that.historicalPrices);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, companyName, startDate, endDate, currentPrice, historicalPrices);
    }
}