package com.intentsg.service.user.controller;

import com.intentsg.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
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

	@GetMapping("/test")
	public ResponseEntity test() {
		List<ServiceInstance> instances = discoveryClient.getInstances("edge-service");
		ServiceInstance serviceInstance = instances.get(0);
		RestTemplate restTemplate = new RestTemplate();
		String forObject = restTemplate.getForObject(serviceInstance.getUri().toString() + "/api/tickets/test", String.class);
		Ticket ticket = new Ticket();
		return ResponseEntity.ok("user-service + data from " + forObject);
	}
}
