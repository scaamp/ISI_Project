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

public class GetBTCUSDCoinbasePrice implements JavaDelegate {
    private static double btcUsdCoinbasePrice;

    static public double getBtcUsdCoinbasePrice() {
        return btcUsdCoinbasePrice;
    }

    public void setBtcUsdCoinbasePrice(int btcUsdCoinbasePrice) {
        GetBTCUSDCoinbasePrice.btcUsdCoinbasePrice = btcUsdCoinbasePrice;
    }

    private final Logger LOGGER = Logger.getLogger(LoggerDelegate.class.getName());

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        LOGGER.info("started GetBTCUSDCoinbasePrice");
        String url = "http://0.0.0.0:8081/coinbase/usd";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        LOGGER.info("resulted with: " + result);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(result);
        String coinbasePriceString = rootNode.path("coinbasePrice").toString().substring(1,
                rootNode.path("coinbasePrice").toString().length()-1);
        btcUsdCoinbasePrice = Double.parseDouble(coinbasePriceString);
        LOGGER.info("Price BTC/USD on Coinbase is: " + btcUsdCoinbasePrice);
    }
}
