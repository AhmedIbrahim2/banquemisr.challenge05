package com.banquemisr.challenge05.controller;


import com.banquemisr.challenge05.model.dto.TaskDto;
import com.banquemisr.challenge05.model.enums.Status;
import com.banquemisr.challenge05.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/create")
    public ResponseEntity<TaskDto> createTask(@Valid @RequestBody TaskDto taskDto) {
        return new ResponseEntity<>(taskService.createTask(taskDto), HttpStatus.CREATED);
    }

    @GetMapping("/paginatedTasks")
    public ResponseEntity<Page<TaskDto>> getTasks(@RequestParam(required = false) Integer page,
                                                  @RequestParam(defaultValue = "10") int size  ){
        return new ResponseEntity<>(taskService.getPaginatedTasks(page, size),HttpStatus.CREATED);
    }

    @PutMapping("/updateTask")
    public ResponseEntity<TaskDto> updateTask(@RequestBody TaskDto taskDto , @RequestParam Long id) {
        return new ResponseEntity<>(taskService.updateTask(taskDto , id), HttpStatus.OK);
    }


    @DeleteMapping("/deleteTask")
    public ResponseEntity<String> deleteTask(@RequestParam Long id) {
        return new ResponseEntity<>(taskService.deleteTask(id),HttpStatus.CREATED);
    }



    @RequestMapping("/getTask/{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable Long id) {
        return new ResponseEntity<>(taskService.getTask(id),HttpStatus.CREATED);
    }

    @PutMapping("/updateStatus/{id}")
    public ResponseEntity<TaskDto> updateTaskStatus(@PathVariable Long id, @RequestParam Status status) {
        return new ResponseEntity<>(taskService.updateTaskStatus(status,id),HttpStatus.OK);
    }


    @GetMapping("/search")
    public ResponseEntity<List<TaskDto>> searchTasks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime dueDate,
            @RequestParam(required = false) Integer page,
            @RequestParam(defaultValue = "10") int size
    )
    {

        return new ResponseEntity<>(taskService.getTasksByCriteria(title, description, status, dueDate , page , size),HttpStatus.CREATED);
    }

}
