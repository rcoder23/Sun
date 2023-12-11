package com.sunbackend.Repository;

import com.sunbackend.Entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface TicketRepo extends JpaRepository<Ticket,Long> {
}
