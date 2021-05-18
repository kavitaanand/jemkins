package com.ankur.inventory.scheduler;

import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@Component
public class FixedRateScheduler {

    @Scheduled(fixedRate = 10000)
    public void fixedRateSch() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        Date now = new Date();
        String strDate = sdf.format(now);
        System.out.println(" --------------Fixed Rate scheduler --------------------------");
        System.out.println(findById());
        System.out.println("---------------------------------------------------------------");
    }

    public ResponseEntity<String> findById(){
        final String uri = "http://localhost:7000/inventory/services/findById";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("client-id","aa");
        String request ="{\n" +
                "  \"itemId\": 1\n" +
                "}";
        HttpEntity<String> entity = new HttpEntity<String>(request, headers);
        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
        return result;
    }
}
