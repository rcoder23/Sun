package com.sunbackend.Controllers;

import com.sunbackend.Entities.Ticket;
import com.sunbackend.Helper.TicketAssign;
import com.sunbackend.Helper.TicketStatus;
import com.sunbackend.Services.TicketServices;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ticket")
@CrossOrigin("*")
public class TicketController {

    @Autowired
    private TicketServices ticketServices;

    @PostMapping("/create")
    @CachePut(cacheNames = "tickets", key = "#ticket.id")
    public ResponseEntity<String> create(@RequestBody Ticket ticket) {
        try {
            if (ticketServices.create(ticket)) {
                return ResponseEntity.ok("Ticket Created");
            } else {
                return ResponseEntity.ok("User Not Exists..");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("/assign")
    @CachePut(cacheNames = "assigned", key = "#ticketAssign.id")
    public ResponseEntity<String> setAsssignee(@RequestBody TicketAssign ticketAssign) {
        try {
            if (ticketServices.setAssign(ticketAssign)) {
                return ResponseEntity.ok("Ticket Assigned Successfully ");
            } else {
                return ResponseEntity.ok("Unable to Assign ticket !!!");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.toString());
        }
    }

    @PostMapping("/unassign")
    @CachePut(cacheNames = "assigned", key = "#ticketAssign.id")
    public ResponseEntity<String> unAssingee(@RequestBody TicketAssign ticketunAssign) {
        try {
            if (ticketServices.unAssign(ticketunAssign)) {
                return ResponseEntity.ok("Ticket unassigned Successfully ");
            } else {
                return ResponseEntity.ok("Unable to unassign ticket !!!");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.toString());
        }
    }

    @GetMapping("/status/{ticketId}")
    public ResponseEntity<String> checkStatus(@PathVariable Long ticketId) {
        if (!ticketServices.isExists(ticketId)) {
            return ResponseEntity.ok("Ticket is Not exists !!!");
        }
        if (ticketServices.checkStatus(ticketId)) {
            return ResponseEntity.ok("Ticket is Already Assigned ");
        } else {
            return ResponseEntity.ok("Ticket is Not Assigned ");
        }
    }

    @RateLimiter(name = "TicketRateLimiter")
    @GetMapping("/getAll")
    public ResponseEntity<List<Ticket>> getAllTicket() {
        return ResponseEntity.ok(ticketServices.getAll());
    }

    @GetMapping("/getById/{ticketId}")
    @Cacheable(cacheNames = "tickets", key = "#ticketId")
    public ResponseEntity<Ticket> getById(@PathVariable Long ticketId) {
        if (!ticketServices.isExists(ticketId)) {
            return ResponseEntity.status(500).body(null);
        } else {
            return ticketServices.getById(ticketId);
        }
    }

    @DeleteMapping("delete/{id}")
    @CacheEvict(cacheNames = "tickets", key = "#id")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        if (ticketServices.deleteById(id)) {
            return ResponseEntity.ok("Deleted successfully");
        } else {
            return ResponseEntity.ok("Can't delete");
        }
    }

    @PostMapping("update/{id}/{status}")
    @CachePut(cacheNames = "tickets", key = "#id")
    public ResponseEntity<Ticket> updateStatus(@PathVariable Long id, @PathVariable String status) {
        if (!ticketServices.isExists(id)) {
            return ResponseEntity.status(500).body(null);
        } else {

            return ResponseEntity.ok(ticketServices.updateTicket(id, TicketStatus.valueOf(status)));
        }

    }


}
