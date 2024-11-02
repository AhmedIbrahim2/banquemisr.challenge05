package com.banquemisr.challenge05.model;


import com.banquemisr.challenge05.model.enums.Role;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "name", nullable = false)
    @NotBlank(message = "Name is required")
    private String username;


    @NotBlank(message = "Password is required")
    @Column(name = "password")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;



    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;


    @OneToMany(mappedBy = "creator", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference  // Use this to indicate the forward part of the relationship
    private List<Task> createdTasks;



    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Notification> notifications;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
