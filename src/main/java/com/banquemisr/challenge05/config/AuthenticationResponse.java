package com.banquemisr.challenge05.config;


import lombok.*;

@Data
@NoArgsConstructor
@Setter
@Getter
public class AuthenticationResponse {
    String token;

    public AuthenticationResponse(String token) {
        this.token = token;
    }
}
