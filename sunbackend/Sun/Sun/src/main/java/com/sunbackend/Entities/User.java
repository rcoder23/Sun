package com.sunbackend.Entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class User {

    //id
    //name
    //email
    //passowrd
    //designation
    //role => (Admin , Develoeper, QA)


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String name;
    private String email;
    private String password;
    private String designation;
    private String role;


    private List<Long> userAssignTickets; //id of tickets

}
