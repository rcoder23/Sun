package com.sunbackend.Entities;

import com.sunbackend.Helper.TicketStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@Entity
public class Ticket {
    //what is important things for ticket
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Long assigneeId; //assign id
    private Date creationDate;
    private Date dueDate;
    private TicketStatus status;
    private String[] comments;
    private String priority;
    private String categroy;
    private String logs;
}
