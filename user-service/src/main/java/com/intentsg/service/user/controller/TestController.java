package com.intentsg.service.user.controller;

import com.intentsg.model.Ticket;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Controller
@RequestMapping("/users")
public class TestController {

	@Autowired
	private DiscoveryClient discoveryClient;

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/test")
	public ResponseEntity test() {
		List<ServiceInstance> instances = discoveryClient.getInstances("edge-service");
		ServiceInstance serviceInstance = instances.get(0);
		String forObject = restTemplate.getForObject(serviceInstance.getUri().toString() + "/api/tickets/test", String.class);
		System.out.println(forObject);
		return ResponseEntity.ok("user-service - " + forObject);

	}

	@GetMapping("/ticket")
	public void addTicket() {
		Ticket ticket = new Ticket();
		ticket.setId("123");
		ticket.setPlace(111);

		ServiceInstance serviceInstance = getServiceInstances();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		JSONObject ticketJson = new JSONObject(ticket);

		HttpEntity<String> request =
				new HttpEntity<>(ticketJson.toString(), headers);

		restTemplate.postForObject(serviceInstance.getUri().toString() + "/api/tickets/ticket", request, Ticket.class);
	}

	private ServiceInstance getServiceInstances() {
		return discoveryClient.getInstances("edge-service").get(0);
	}
}
