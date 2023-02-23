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

public class GetCurrencyClient implements JavaDelegate {
    private final Logger LOGGER = Logger.getLogger(LoggerDelegate.class.getName());

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        LOGGER.info("started GetCurrencyClient");
        String url = "http://0.0.0.0:8081/currency";
        RestTemplate restTemplate = new RestTemplate();
        JSONObject currencyJsonObject = new JSONObject();
        String currency = (String) delegateExecution.getVariable("currency");
        currencyJsonObject.put("currency", currency);
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(currencyJsonObject.toString(),
                header);
        String result = restTemplate.postForObject(url, request, String.class);
        LOGGER.info("GetCurrencyClient result " + result);
    }
}
