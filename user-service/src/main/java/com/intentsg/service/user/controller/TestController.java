package com.intentsg.service.user.controller;

import com.intentsg.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/users")
public class TestController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/test")
    public ResponseEntity<?> test() {
        List<ServiceInstance> instances = discoveryClient.getInstances("edge-service");
        ServiceInstance serviceInstance = instances.get(0);
        String forObject = restTemplate.getForObject(serviceInstance.getUri().toString() + "/api/tickets/test", String.class);
        System.out.println(forObject);
        return ResponseEntity.ok("user-service - " + forObject);

    }

    @GetMapping(value = "/ticket/create", params = {"id", "place"})
    public ResponseEntity<?> addTicket(@RequestParam String id, @RequestParam int place) {
        ServiceInstance serviceInstance = getServiceInstances();
        HttpEntity<Ticket> requestEntity = getHttpEntity(id, place);
        restTemplate.postForObject(
                serviceInstance.getUri().toString() + "/api/tickets/ticket", requestEntity, Ticket.class
        );
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/ticket/update", params = {"id", "place"})
    public ResponseEntity<?> updateTicket(@RequestParam String id, @RequestParam int place) {
        ServiceInstance serviceInstance = getServiceInstances();
        HttpEntity<Ticket> requestEntity = getHttpEntity(id, place);
        restTemplate.put(serviceInstance.getUri().toString() + "/api/tickets/update", requestEntity, Ticket.class);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/ticket/delete/{id}")
    public ResponseEntity<?> deleteTicket(@PathVariable String id) {
        Map<String, String> params = new HashMap<String, String>() {{
            put("id", id);
        }};
        ServiceInstance serviceInstance = getServiceInstances();
        restTemplate.delete(serviceInstance.getUri().toString() + "/api/tickets/delete/{id}", params);
        return ResponseEntity.ok().build();
    }

    private ServiceInstance getServiceInstances() {
        return discoveryClient.getInstances("edge-service").get(0);
    }

    private HttpEntity<Ticket> getHttpEntity(String id, int place) {
        Ticket ticket = new Ticket();
        ticket.setId(id);
        ticket.setPlace(place);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(ticket, headers);
    }
}
