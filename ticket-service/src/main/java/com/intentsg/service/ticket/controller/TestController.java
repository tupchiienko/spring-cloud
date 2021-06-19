package com.intentsg.service.ticket.controller;

import com.intentsg.model.Ticket;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/tickets")
public class TestController {

    List<Ticket> ticketList = new ArrayList<>();

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("ticket-service 8084");
    }

    @GetMapping("/tickets")
    public ResponseEntity<List<Ticket>> getTicket() {
        return ResponseEntity.ok(ticketList);
    }

    @PostMapping("/ticket")
    public ResponseEntity<?> addTicket(@RequestBody Ticket ticket) {
        if (ticketList.contains(ticket)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        ticketList.add(ticket);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateTicket(@RequestBody Ticket ticket) {
        for (Ticket t : ticketList) {
            if (t.getId().equals(ticket.getId())) {
                t.setPlace(ticket.getPlace());
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTicket(@PathVariable String id) {
        for (Ticket t : ticketList) {
            if (t.getId().equals(id)) {
                ticketList.remove(t);
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.notFound().build();
    }
}
