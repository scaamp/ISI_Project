package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import spinjar.com.minidev.json.JSONObject;

import java.util.logging.Logger;

public class GetBTCEURBinancePrice implements JavaDelegate {

    static private double btcEurBinancePrice;

    static public double getBtcEurBinancePrice() {
        return btcEurBinancePrice;
    }

    public void setBtcEurBinancePrice(int btcEurBinancePrice) {
        GetBTCEURBinancePrice.btcEurBinancePrice = btcEurBinancePrice;
    }

    private final Logger LOGGER = Logger.getLogger(LoggerDelegate.class.getName());

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        LOGGER.info("started GetBTCEURBinancePrice");
        String url = "http://0.0.0.0:8081/binance/eur";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(result);
        String binancePriceString = rootNode.path("binancePrice").toString().substring(1,
                rootNode.path("binancePrice").toString().length()-1);
        btcEurBinancePrice = Double.parseDouble(binancePriceString);
        LOGGER.info("Price BTC/EUR on Binance is: " + btcEurBinancePrice);
    }

}
