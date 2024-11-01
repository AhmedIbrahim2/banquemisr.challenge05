package com.example.banquemisr.controller;


import com.example.banquemisr.model.dto.TaskDto;
import com.example.banquemisr.model.enums.Status;
import com.example.banquemisr.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/create")
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto) {
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



}
