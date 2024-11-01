package com.example.banquemisr.model;

import com.example.banquemisr.model.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.security.Timestamp;
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



    @ManyToOne
    @JoinColumn(name = "task_id",nullable = false)
    private Task task;

}
