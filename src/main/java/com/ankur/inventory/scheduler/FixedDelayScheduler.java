package com.ankur.inventory.scheduler;

import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@Component
public class FixedDelayScheduler {
    @Scheduled(fixedDelay = 30000, initialDelay = 3000)
    public void fixedDelaySch() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();
        String strDate = sdf.format(now);
        System.out.println(" --------------Fixed Delay scheduler --------------------------");
        System.out.println(listAll());
        System.out.println("---------------------------------------------------------------");
    }

    public ResponseEntity<String> listAll(){
        final String uri = "http://localhost:7000/inventory/services/listall";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("client-id","aa");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
        return result;
    }
}
