package com.sunbackend.Controllers;


import com.sunbackend.Entities.User;
import com.sunbackend.Services.AuthServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
public class UserAuth {

    @Autowired
    private AuthServices authServices;

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody User user){

        if(authServices.save(user)){
            return ResponseEntity.ok("User Saved Successfully");
        }else {
            return ResponseEntity.badRequest().body("Email is alredy registered..");
        }
    }

    @GetMapping("/getuser/{userId}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable Long userId){
        return ResponseEntity.ok(authServices.getUserById(userId));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAllUser(){
        return ResponseEntity.ok(authServices.getAllUser());
    }

}
