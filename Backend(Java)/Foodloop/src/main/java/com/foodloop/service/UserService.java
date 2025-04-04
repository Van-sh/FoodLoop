package com.foodloop.service;

import com.foodloop.config.JwtService;
import com.foodloop.entity.User;
import com.foodloop.repo.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    public User findByJwtToken(String token) {
        token = token.substring(7);
        String email = jwtService.extractUsername(token);
        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new EntityNotFoundException("User not found with email: " + email));
        return user;
    }



}
