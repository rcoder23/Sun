package com.sunbackend.Services;


import com.sunbackend.Entities.User;
import com.sunbackend.Helper.UserDto;
import com.sunbackend.Repository.AuthRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthServices {

    @Autowired
    private AuthRepo authRepo;

    public int chandanTemp() {
        return 1;
    }

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

    public User auth(UserDto userDto) {

        User user1 = authRepo.findByEmail(userDto.getEmail());
        if (user1 != null && user1.getPassword().equals(userDto.getPassword())) {
            return user1;
        } else {
            return null;
        }
//        return isFound;
    }
}
