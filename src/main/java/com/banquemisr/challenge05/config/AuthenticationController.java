package com.banquemisr.challenge05.config;


import com.banquemisr.challenge05.model.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    @Autowired
    private  AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody User user) {
        return ResponseEntity.ok(authenticationService.register(user));
    }


    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody User user) {

        return ResponseEntity.ok(authenticationService.authenticate(user));


    }
}
