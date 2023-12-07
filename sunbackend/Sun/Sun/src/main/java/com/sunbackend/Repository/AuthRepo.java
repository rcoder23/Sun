package com.sunbackend.Repository;

import com.sunbackend.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepo extends JpaRepository<User,Long> {
    User findByEmail(String email);
}
