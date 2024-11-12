package org.example.utilities;

// handles fetching stock from the API

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class AlphaVantageService {
    private static final String API_KEY = "XLHSNRIBP29WYFZ3";
    private static final String BASE_URL = "https://www.alphavantage.co/query";

    public Map<LocalDate, BigDecimal> getDailyClosingPrices(String symbol, String interval) throws Exception {
        String url = BASE_URL + "?function=TIME_SERIES_DAILY" + "&symbol=" + symbol + "&apikey=" + API_KEY;

        // Step 1: Create a URL object and open a connection
        URL obj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
        connection.setRequestMethod("GET");

        // Step 2: Read the response
        BufferedReader incomingData = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String inputLine;
        while ((inputLine = incomingData.readLine()) != null) {
            response.append(inputLine);
        }
        incomingData.close();

        // Step 3: Parse JSON response to extract dates and closing prices
        Map<LocalDate, BigDecimal> dailyClosingPrices = new HashMap<>();
        JSONObject jsonResponse = new JSONObject(response.toString());
        JSONObject timeSeries = jsonResponse.getJSONObject("Time Series (Daily)");

        for (String date : timeSeries.keySet()) {
            LocalDate localDate = LocalDate.parse(date);
            BigDecimal closingPrice = new BigDecimal(timeSeries.getJSONObject(date).getString("4. close"));
            dailyClosingPrices.put(localDate, closingPrice);
        }

        return dailyClosingPrices;
    }
}