package com.sunbackend.Controllers;

import com.sunbackend.Entities.User;
import com.sunbackend.Helper.UserDto;
import com.sunbackend.Services.AuthServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin("*")
public class UserAuth {

    @Autowired
    private AuthServices authServices;

    @PostMapping("/create")
    @CachePut(cacheNames = "user", key = "#user.id")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        if (authServices.save(user)) {
            return ResponseEntity.ok("User Saved Successfully");
        } else {
            return ResponseEntity.badRequest().body("Email is alredy registered..");
        }
    }

    @PostMapping("/auth")
    public ResponseEntity<User> authenticate(@RequestBody UserDto userDto) {
        User user = authServices.auth(userDto);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return (ResponseEntity<User>) ResponseEntity.badRequest();
        }
    }

    @GetMapping("/getuser/{userId}")
    @Cacheable(cacheNames = "user", key = "#userId")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(authServices.getUserById(userId));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAllUser() {
        return ResponseEntity.ok(authServices.getAllUser());
    }

}
