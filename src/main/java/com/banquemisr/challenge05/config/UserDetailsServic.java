package com.banquemisr.challenge05.config;

import com.banquemisr.challenge05.model.User;
import com.banquemisr.challenge05.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServic implements org.springframework.security.core.userdetails.UserDetailsService {


    private final UserRepository userRepository;

    public UserDetailsServic(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(username);
        System.out.println(user.getEmail());
        System.out.println(user.getRole());

       // System.out.println(user);

        if (user == null) {
            throw new UsernameNotFoundException("User with email " + username + " not found");
        }

        return user;
        }
}
