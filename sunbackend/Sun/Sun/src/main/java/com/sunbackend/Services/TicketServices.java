package com.sunbackend.Services;


import com.sunbackend.Entities.Ticket;
import com.sunbackend.Entities.User;
import com.sunbackend.Helper.TicketAssign;
import com.sunbackend.Helper.TicketStatus;
import com.sunbackend.Repository.AuthRepo;
import com.sunbackend.Repository.TicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketServices {

    @Autowired
    private TicketRepo ticketRepo;

    @Autowired
    private AuthRepo authRepo;

    //create new ticket
    public boolean create(Ticket ticket) {
        //checking assign is exists or not
        if (!authRepo.findById(ticket.getAssigneeId()).isPresent()) {
            return false;
        }

        ticketRepo.save(ticket);
        Optional<User> UserWhomTicketToAssign = authRepo.findById(ticket.getAssigneeId());
        if (UserWhomTicketToAssign.isPresent()) {
            User user = UserWhomTicketToAssign.get();
            List<Long> TicketAssignToUser = user.getUserAssignTickets();
            TicketAssignToUser.add(ticket.getId());
            user.setUserAssignTickets(TicketAssignToUser);
            authRepo.save(user);
        }
        return true;
    }


    //assign ticket to user
    public boolean setAssign(TicketAssign ticketToAssign) {
        Optional<User> UserWhomTicketToAssign = authRepo.findById(ticketToAssign.getAssigneeId());
        Optional<Ticket> ticket = ticketRepo.findById(ticketToAssign.getId());

        //checking ticket and user exists or not
        if (UserWhomTicketToAssign.isPresent() && ticket.isPresent()) {

            User user = UserWhomTicketToAssign.get();
            List<Long> TicketAssignToUser = user.getUserAssignTickets();

            //if ticket is already assigned to user or not
            if (TicketAssignToUser.contains(ticketToAssign.getId())) {
                return false;
            }
            TicketAssignToUser.add(ticketToAssign.getId());
            authRepo.save(user);
            ticket.get().setAssigneeId(ticketToAssign.getAssigneeId());
            ticketRepo.save(ticket.get());
            return true;
        }
        return false;
    }

    //unassign ticket to user
    public boolean unAssign(TicketAssign ticketunAssign) {
        Optional<User> UserWhomTicketToUnAssign = authRepo.findById(ticketunAssign.getAssigneeId());
        Optional<Ticket> ticket = ticketRepo.findById(ticketunAssign.getId());

        //checking ticket and user exists or not
        if (UserWhomTicketToUnAssign.isPresent() && ticket.isPresent()) {
            User user = UserWhomTicketToUnAssign.get();
            List<Long> TicketAssignToUser = user.getUserAssignTickets();

            //checking it is alredy unassigned or not
            if (!TicketAssignToUser.contains(ticketunAssign.getId())) return false;
            TicketAssignToUser.remove(ticketunAssign.getId()); //remove ticke id from user list

            authRepo.save(user);
            ticket.get().setAssigneeId(null); //unassigned user id
            ticketRepo.save(ticket.get());
            return true;
        }
        return false;
    }


    //checking the status of ticket
    public boolean checkStatus(Long id) {
        Optional<Ticket> ticket = ticketRepo.findById(id);
        if (ticket.isPresent()) {
            System.out.println(ticket.get().getAssigneeId());
            if (ticket.get().getAssigneeId() != null) {
                return true; // assigned to some one
            }
        }
        return false;
    }

    //to check ticket exists or not
    public boolean isExists(Long id) {
        return ticketRepo.findById(id).isPresent();
    }

    //to get all ticket
    public List<Ticket> getAll() {
        return ticketRepo.findAll();
    }

    //to get ticket by id
    public ResponseEntity<Ticket> getById(Long ticketId) {
        Optional<Ticket> byId = ticketRepo.findById(ticketId);
        if (byId.isPresent()) {
            return ResponseEntity.ok(byId.get());
        }
        return null;
    }

    //delete ticket by id
    public boolean deleteById(Long id) {
        if (checkStatus(id)) {
            Optional<Ticket> byId = ticketRepo.findById(id);
            if (byId.isPresent()) {
                if (unAssign(new TicketAssign(id, byId.get().getAssigneeId()))) {
                    ticketRepo.deleteById(id);
                    return true;
                }
            }
            return false;
        } else {
            ticketRepo.deleteById(id);
            return true;
        }
    }

    public Ticket updateTicket(Long id, TicketStatus status) {
        Optional<Ticket> ticket = ticketRepo.findById(id);
        if (ticket.isPresent()) {
            ticket.get().setStatus(status);
            Ticket save = ticketRepo.save(ticket.get());
            return save;
        } else {
            return null;
        }
    }
}
