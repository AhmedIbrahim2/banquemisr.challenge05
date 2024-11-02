package com.banquemisr.challenge05.model;


import com.banquemisr.challenge05.model.enums.Priority;
import com.banquemisr.challenge05.model.enums.Status;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Future;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "_tasks")
@Entity
public class Task {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long id;

    @NotBlank(message = "Title is required")
    @Column(name = "title", nullable = false)
    private String title;


    @Column(name = "description")
    @Size(max = 500, message = "Description can't exceed 500 characters")
    private String description;


    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "priority")
    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Future(message = "Due date must be in the future")
    @Column(name = "due_date")
    private LocalDateTime dueDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "creator_id")
    @JsonBackReference  // Use this to indicate the back part of the relationship
    private User creator;



    @OneToMany(mappedBy = "task" ,  cascade = CascadeType.REMOVE)
    private List<History> historyRecords;

}
