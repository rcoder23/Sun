package com.sunbackend.Services;


import com.sunbackend.Entities.User;
import com.sunbackend.Repository.AuthRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.desktop.SystemEventListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthServices {

    @Autowired
    private AuthRepo authRepo;

    public boolean save(User user) {
        if (authRepo.findByEmail(user.getEmail()) == null) {
            if (user.getUserAssignTickets() == null) {
                user.setUserAssignTickets(new ArrayList<Long>());
            }
            authRepo.save(user);
            return true;
        } else {
            return false;
        }
    }

    public Optional<User> getUserById(Long id) {
        return authRepo.findById(id);
    }

    public List<User> getAllUser() {
        return authRepo.findAll();
    }
}
