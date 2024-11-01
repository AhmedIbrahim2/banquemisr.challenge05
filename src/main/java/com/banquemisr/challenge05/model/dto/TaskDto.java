package com.banquemisr.challenge05.model.dto;

import com.banquemisr.challenge05.model.Task;
import com.banquemisr.challenge05.model.User;
import com.banquemisr.challenge05.model.enums.Priority;
import com.banquemisr.challenge05.model.enums.Status;
import lombok.*;

import java.time.LocalDateTime;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class TaskDto {

    private Long id;
    private String title;
    private String description;
    private Status status;
    private Priority priority;
    private LocalDateTime dueDate;
    private Long creatorId;



    public static TaskDto toDto(Task task) {
        TaskDto dto = new TaskDto();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setStatus(task.getStatus());
        dto.setPriority(task.getPriority());
        dto.setDueDate(task.getDueDate());
        dto.setCreatorId(task.getCreator().getId());
        return dto;
    }

    public static Task toEntity(TaskDto dto , User creator) {
        Task task = new Task();
        task.setId(dto.getId());
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus());
        task.setPriority(dto.getPriority());
        task.setDueDate(dto.getDueDate());
        task.setCreator(creator);
        return task;
    }
}
