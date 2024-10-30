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
    private Map<LocalDate, BigDecimal> historicalPrices;

    public StockModel() {
    }

    public StockModel(String symbol, String companyName, LocalDate startDate, LocalDate endDate, Map<LocalDate, BigDecimal> historicalPrices) {
        this.symbol = symbol;
        this.companyName = companyName;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public Map<LocalDate, BigDecimal> getHistoricalPrices() {
        return historicalPrices;
    }

    public void setHistoricalPrices(Map<LocalDate, BigDecimal> historicalPrices) {
        this.historicalPrices = historicalPrices;
    }

    @Override
    public String toString() {
        return "StockModel{" +
                "symbol='" + symbol + '\'' +
                ", companyName='" + companyName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", historicalPrices=" + historicalPrices +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockModel that = (StockModel) o;
        return Objects.equals(symbol, that.symbol) && Objects.equals(companyName, that.companyName) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate) && Objects.equals(historicalPrices, that.historicalPrices);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, companyName, startDate, endDate, historicalPrices);
    }
}