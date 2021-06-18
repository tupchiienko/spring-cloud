package com.intentsg.service.ticket.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.intentsg.model.Ticket;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/tickets")
public class TestController {

	List<Ticket> ticketList = new ArrayList<>();

	@GetMapping("/test")
	public ResponseEntity test() {
		return ResponseEntity.ok("ticket-service 8084");
	}

	@GetMapping("/tickets")
	public ResponseEntity<List<Ticket>> getTicket() {
		return ResponseEntity.ok(ticketList);
	}

	@PostMapping("/ticket")
	public void addTicket(@RequestBody Ticket ticket) {
		ticketList.add(ticket);
	}
}
