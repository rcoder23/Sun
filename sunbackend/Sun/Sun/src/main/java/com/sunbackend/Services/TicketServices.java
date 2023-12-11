package com.sunbackend.Services;


import com.sunbackend.Entities.Ticket;
import com.sunbackend.Entities.User;
import com.sunbackend.Helper.TicketAssign;
import com.sunbackend.Repository.AuthRepo;
import com.sunbackend.Repository.TicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketServices {

    @Autowired
    private TicketRepo ticketRepo;

    @Autowired
    private AuthRepo authRepo;


    public void create(Ticket ticket){
        ticketRepo.save(ticket);
        Optional<User> UserWhomTicketToAssign = authRepo.findById(ticket.getAssigneeId());

        if(UserWhomTicketToAssign.isPresent()){


            User user=UserWhomTicketToAssign.get();
            List<Long> TicketAssignToUser=user.getUserAssignTickets();
            TicketAssignToUser.add(ticket.getId());

//            System.out.println("TEST-5" +TicketAssignToUser);

            user.setUserAssignTickets(TicketAssignToUser);
             authRepo.save(user);
        }
    }


    public void setAssign(TicketAssign ticketAssign) {
        Optional<Ticket> ticketToAssign = ticketRepo.findById(ticketAssign.getId());
        if(ticketAssign!=null){
           //need to as
        }

    }
}
