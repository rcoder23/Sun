package com.sunbackend.Controllers;

import com.sunbackend.Entities.Ticket;
import com.sunbackend.Helper.TicketAssign;
import com.sunbackend.Services.TicketServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/ticket")
public class TicketController {

    @Autowired
    private TicketServices ticketServices;

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody Ticket ticket){
        //here only ticket is created and currently assigned is null , priority is null,dueDate
        //can be null
        //we need to valid ticket as well

        try {
            ticketServices.create(ticket);
            return ResponseEntity.ok("Ticket Created");
        }catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    public ResponseEntity<String> setAsssignee(@RequestBody TicketAssign ticketAssign){
        try{
            ticketServices.setAssign(ticketAssign);
            return ResponseEntity.ok("Ticket Created");
        }catch (Exception e){
            return ResponseEntity.status(500).build();
        }
    }


}
