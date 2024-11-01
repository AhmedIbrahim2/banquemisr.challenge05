package com.banquemisr.challenge05.model;


import com.banquemisr.challenge05.model.enums.Role;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;


    @Column(name = "password")
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;


    @OneToMany(mappedBy = "creator")
    @JsonManagedReference  // Use this to indicate the forward part of the relationship
    private List<Task> createdTasks;



    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Notification> notifications;

}
