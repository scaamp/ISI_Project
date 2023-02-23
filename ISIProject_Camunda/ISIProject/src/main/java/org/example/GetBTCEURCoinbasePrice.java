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

public class GetBTCEURCoinbasePrice implements JavaDelegate {
    private static double btcEurCoinbasePrice;

    static public double getBtcEurCoinbasePrice() {
        return btcEurCoinbasePrice;
    }

    public void setBtcEurCoinbasePrice(int btcEurCoinbasePrice) {
        GetBTCEURCoinbasePrice.btcEurCoinbasePrice = btcEurCoinbasePrice;
    }

    private final Logger LOGGER = Logger.getLogger(LoggerDelegate.class.getName());

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        LOGGER.info("started GetBTCEURCoinbasePrice");
        String url = "http://0.0.0.0:8081/coinbase/eur";
        RestTemplate restTemplate = new RestTemplate();
        JSONObject iceCreamJsonObject = new JSONObject();
        String coinbasePrice = (String) delegateExecution.getVariable("coinbasePrice");
        iceCreamJsonObject.put("coinbasePrice", coinbasePrice);

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(iceCreamJsonObject.toString(),
                header);
        String result = restTemplate.getForObject(url, String.class);
        LOGGER.info("resulted with: " + result);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(result);
        String coinbasePriceString = rootNode.path("coinbasePrice").toString().substring(1,
                rootNode.path("coinbasePrice").toString().length()-1);
        btcEurCoinbasePrice = Double.parseDouble(coinbasePriceString);
        LOGGER.info("Price BTC/EUR on Coinbase is: " + btcEurCoinbasePrice);
    }
}
