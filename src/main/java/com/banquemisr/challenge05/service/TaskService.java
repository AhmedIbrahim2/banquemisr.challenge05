package com.banquemisr.challenge05.service;

import com.banquemisr.challenge05.model.dto.TaskDto;
import com.banquemisr.challenge05.model.enums.Status;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TaskService {


    public TaskDto createTask(TaskDto task);
    public TaskDto updateTask(TaskDto task , Long id);
    public TaskDto updateTaskStatus(Status status , Long id);
    public TaskDto  getTask(Long id);
//    public List<TaskDto> getAllTasks();
    Page<TaskDto> getPaginatedTasks(int page, int size);
     String  deleteTask(Long id);



    public List<TaskDto> getTasksByCriteria(String title, String description, Status status, LocalDateTime dueDate , int page, int size) ;

}
