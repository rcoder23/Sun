package com.sunbackend.Services;


import com.sunbackend.Entities.Ticket;
import com.sunbackend.Helper.TicketAssign;
import com.sunbackend.Repository.TicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TicketServices {

    @Autowired
    private TicketRepo ticketRepo;


    public void create(Ticket ticket){
        ticketRepo.save(ticket);

    }


    public void setAssign(TicketAssign ticketAssign) {
        Optional<Ticket> ticketToAssign = ticketRepo.findById(ticketAssign.getId());
        if(ticketAssign!=null){
           //need to as
        }

    }
}
