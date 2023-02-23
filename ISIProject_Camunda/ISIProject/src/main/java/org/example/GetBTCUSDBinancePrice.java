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

public class GetBTCUSDBinancePrice implements JavaDelegate {
    private static double btcUsdBinancePrice;

    static public double getBtcUsdBinancePrice() {
        return btcUsdBinancePrice;
    }

    public void setBtcUsdBinancePrice(int btcUsdBinancePrice) {
        GetBTCUSDBinancePrice.btcUsdBinancePrice = btcUsdBinancePrice;
    }

    private final Logger LOGGER = Logger.getLogger(LoggerDelegate.class.getName());

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        LOGGER.info("started GetBTCUSDBinancePrice");
        String url = "http://0.0.0.0:8081/binance/usd";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        LOGGER.info("resulted with: " + result);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(result);
        String binancePriceString = rootNode.path("binancePrice").toString().substring(1,
                rootNode.path("binancePrice").toString().length()-1);
        btcUsdBinancePrice = Double.parseDouble(binancePriceString);
        LOGGER.info("Price BTC/USD on Binance is: " + btcUsdBinancePrice);
    }
}
