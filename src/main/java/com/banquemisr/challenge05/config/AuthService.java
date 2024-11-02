package com.banquemisr.challenge05.config;


import com.banquemisr.challenge05.model.User;
import com.banquemisr.challenge05.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            String email =  ((User) authentication.getPrincipal()).getEmail();
            User user = userRepository.findUserByEmail(email);  // Assuming you have findByEmail in UserRepository

            if (email == null ) {
                throw new RuntimeException("User not found");
            }

            return ((User) authentication.getPrincipal()).getId();
        }
        throw new RuntimeException("User not authenticated");
    }

}
