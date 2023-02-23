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

public class GetBTCUSDKucoinPrice implements JavaDelegate {
    private static double btcUsdKucoinPrice;

    static public double getBtcUsdKucoinPrice() {
        return btcUsdKucoinPrice;
    }

    public void setBtcUsdKucoinPrice(int btcUsdKucoinPrice) {
        GetBTCUSDKucoinPrice.btcUsdKucoinPrice = btcUsdKucoinPrice;
    }

    private final Logger LOGGER = Logger.getLogger(LoggerDelegate.class.getName());

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        LOGGER.info("started GetBTCUSDKucoinPrice");
        String url = "http://0.0.0.0:8081/kucoin/usd";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        LOGGER.info("resulted with: " + result);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(result);
        String kucoinPriceString = rootNode.path("kucoinPrice").toString().substring(1,
                rootNode.path("kucoinPrice").toString().length()-1);
        btcUsdKucoinPrice = Double.parseDouble(kucoinPriceString);
        LOGGER.info("Price BTC/USD on Kucoin is: " + btcUsdKucoinPrice);
    }
}
