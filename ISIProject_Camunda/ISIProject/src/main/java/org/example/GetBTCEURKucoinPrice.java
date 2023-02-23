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

public class GetBTCEURKucoinPrice implements JavaDelegate {
    private static double btcEurKucoinPrice;

    static public double getBtcEurKucoinPrice() {
        return btcEurKucoinPrice;
    }

    public void setBtcEurKucoinPrice(int btcEurKucoinPrice) {
        GetBTCEURKucoinPrice.btcEurKucoinPrice = btcEurKucoinPrice;
    }

    private final Logger LOGGER = Logger.getLogger(LoggerDelegate.class.getName());

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        LOGGER.info("started GetBTCEurKucoinPrice");
        String url = "http://0.0.0.0:8081/kucoin/eur";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        LOGGER.info("resulted with: " + result);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(result);
        String kucoinPriceString = rootNode.path("kucoinPrice").toString();
        btcEurKucoinPrice = Double.parseDouble(kucoinPriceString);
        LOGGER.info("Price BTC/Eur on Kucoin is: " + btcEurKucoinPrice);
    }
}
