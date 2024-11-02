package com.banquemisr.challenge05.model;

import com.banquemisr.challenge05.model.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "_history")
@Entity
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Status oldStatus;

    @Enumerated(EnumType.STRING)
    private Status newStatus;

//
//    @CreationTimestamp
//    private Timestamp changeDate;

    private LocalDateTime changeDate;  // Changed to LocalDateTime



    @ManyToOne(cascade = CascadeType.REMOVE , fetch = FetchType.EAGER)
    @JoinColumn(name = "task_id",nullable = false   )
    private Task task;

}
