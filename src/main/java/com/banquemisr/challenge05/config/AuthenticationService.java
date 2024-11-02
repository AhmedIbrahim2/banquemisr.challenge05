package com.banquemisr.challenge05.config;


import com.banquemisr.challenge05.model.User;
import com.banquemisr.challenge05.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {


    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }



    public AuthenticationResponse register(User request){
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setRole(request.getRole());

        user = userRepository.save(user);
        String token = JwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }


    public AuthenticationResponse authenticate(User request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));
        User user = userRepository.findUserByEmail(request.getEmail());
        String token = JwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }
}
