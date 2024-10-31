package org.example.utilities;

// handles fetching stock from the API

import org.example.domain.StockModel;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;

public class AlphaVantageService {
    private static final String API_KEY = "XLHSNRIBP29WYFZ3";
    private static final String BASE_URL = "https://www.alphavantage.co/query";

    public String getStockData(String symbol, String interval) throws Exception {
        String url = BASE_URL + "?function=TIME_SERIES_INTRADAY" + "&symbol=" + symbol + "&interval=" + interval + "&apikey=" + API_KEY;

        // step 1: create a URL object and open a connection
        URL obj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
        connection.setRequestMethod("GET");

        // step 2: read the response
        BufferedReader incomingData = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = incomingData.readLine()) != null) {
            response.append(inputLine);
        }
        incomingData.close();
        return response.toString(); // step 3: parse json response
    }

    private StockModel parseStockData(String jsonResponse) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonResponse);
        JSONObject timeSeries = jsonObject.getJSONObject("Time Series (1 minute)");

        // get the most recent entry (stock data for latest timestamp)
        String latestTime = timeSeries.keys().next(); // first key is the latest timestamp
        JSONObject latestData = timeSeries.getJSONObject(latestTime);

        // extract stock data
        BigDecimal currentPrice = latestData.getBigDecimal("1. current price");

        // create and return a stock object
        return new StockModel("AAPL", "Apple Inc.", currentPrice);
    }
}