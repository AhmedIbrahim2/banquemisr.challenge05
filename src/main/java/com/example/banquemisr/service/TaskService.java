package com.example.banquemisr.service;

import com.example.banquemisr.model.dto.TaskDto;
import com.example.banquemisr.model.enums.Status;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface TaskService {


    public TaskDto createTask(TaskDto task);
    public TaskDto updateTask(TaskDto task , Long id);
    public TaskDto updateTaskStatus(Status status , Long id);
    public TaskDto  getTask(Long id);
//    public List<TaskDto> getAllTasks();
    Page<TaskDto> getPaginatedTasks(int page, int size);
     String  deleteTask(Long id);

}
