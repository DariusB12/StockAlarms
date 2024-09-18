package org.example.stockalarms.integrationTests;

import org.example.stockalarms.exceptions.customExceptions.AlphaVantageException;
import org.example.stockalarms.integrationTests.configuration.AlphaVantageServiceITConfiguration;
import org.example.stockalarms.service.alphaVantage.AlphaVantageService;
import org.example.stockalarms.utils.alphaVantage.json.MetaData;
import org.example.stockalarms.utils.alphaVantage.json.TimeSeries;
import org.example.stockalarms.utils.alphaVantage.json.TimeSeriesIntradayResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AlphaVantageServiceITConfiguration.class)
public class AlphaVantageServiceIT {
    @Autowired
    public AlphaVantageService alphaVantageService;

    /**
     * test that the response retrieved from the alpha vantage api is handled properly
     */
    @Test
    public void whenRequestTimeSeriesIntraday_thenReturnTimeSeriesIntradayResponse() throws AlphaVantageException {
        TimeSeriesIntradayResponse response = alphaVantageService.getTimeSeriesIntradayResponse(null);

        assertNotNull(response);
        assertNotNull(response.getMetaData());
        assertNotNull(response.getTimeSeries());
        assertNull(response.getError());

        MetaData metaData = response.getMetaData();
        assertNotNull(metaData.getInformation());
        assertNotNull(metaData.getSymbol());
        assertNotNull(metaData.getLastRefreshed());
        assertNotNull(metaData.getInterval());
        assertNotNull(metaData.getOutputSize());
        assertNotNull(metaData.getTimeZone());

        Map<String,TimeSeries> timeSeriesMap = response.getTimeSeries();
        for(Map.Entry<String,TimeSeries> entry : timeSeriesMap.entrySet()){
            assertNotNull(entry.getKey());
            assertNotNull(entry.getValue());

            TimeSeries value = entry.getValue();
            assertNotNull(value.getOpen());
            assertNotNull(value.getHigh());
            assertNotNull(value.getLow());
            assertNotNull(value.getClose());
            assertNotNull(value.getVolume());
        }
    }
    @Test
    public void whenRequestTimeSeriesIntraday_thenReturnError() throws AlphaVantageException {
        TimeSeriesIntradayResponse response = alphaVantageService.getTimeSeriesIntradayResponse("");

        assertNotNull(response);
        assertNull(response.getMetaData());
        assertNull(response.getTimeSeries());
        assertNotNull(response.getError());
    }

}
