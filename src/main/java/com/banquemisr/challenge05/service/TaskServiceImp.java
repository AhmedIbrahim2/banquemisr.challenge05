package com.banquemisr.challenge05.service;


import com.banquemisr.challenge05.config.AuthService;
import com.banquemisr.challenge05.exception.NotFoundException;
import com.banquemisr.challenge05.model.History;
import com.banquemisr.challenge05.model.Task;
import com.banquemisr.challenge05.model.TaskSpecification;
import com.banquemisr.challenge05.model.User;
import com.banquemisr.challenge05.model.dto.TaskDto;
import com.banquemisr.challenge05.model.enums.Status;
import com.banquemisr.challenge05.repository.HistoryRepository;
import com.banquemisr.challenge05.repository.TaskRepository;
import com.banquemisr.challenge05.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImp implements TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private AuthService authService;

    @Override
    @Transactional
    public TaskDto createTask(TaskDto task) {

        Long id =  authService.getCurrentUserId();
        User creator = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        task.setCreatorId(id);
        Task task1 = taskRepository.save(TaskDto.toEntity(task, creator));
        String notificationMessage = "A new task has been created: " + task1.getTitle() +
                ". Due date: " + task.getDueDate();
        notificationService.createNotification(creator, notificationMessage);

        return TaskDto.toDto(task1);
    }

    @Override
    public TaskDto updateTask(TaskDto task , Long id) {
        Task task1 = taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task not found"));

        if (!task1.getStatus().equals(task.getStatus())){
            History history = new History();
            history.setOldStatus(task1.getStatus());
            history.setNewStatus(task.getStatus());
            history.setChangeDate(LocalDateTime.now());  // Manually set the timestamp
            history.setTask(task1);
            historyRepository.save(history);
        }

        if (task.getTitle() != null) {
            task1.setTitle(task.getTitle());
        }
        if (task.getDescription() != null) {
            task1.setDescription(task.getDescription());
        }
        if (task.getStatus() != null) {
            task1.setStatus(task.getStatus());
        }
        if (task.getPriority() != null) {
            task1.setPriority(task.getPriority());
        }
        if (task.getDueDate() != null) {
            task1.setDueDate(task.getDueDate());
        }

      //  BeanUtils.copyProperties(task, task1,"id");


        return TaskDto.toDto(taskRepository.save(task1));

    }

    @Override
    public TaskDto updateTaskStatus(Status status, Long id) {
        Task task1 = taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task not found"));

        History history = new History();
        history.setOldStatus(task1.getStatus());
        history.setNewStatus(status);
        history.setChangeDate(LocalDateTime.now());  // Manually set the timestamp
        history.setTask(task1);
        historyRepository.save(history);

        task1.setStatus(status);
        taskRepository.save(task1);

        String notificationMessage = "Status of Task :" + task1.getTitle() +
                ". Due date: " + task1.getDueDate() +"  has been Updated Into: "+task1.getStatus() ;
        notificationService.createNotification(task1.getCreator(), notificationMessage);

        return TaskDto.toDto(task1);
    }



    @Override
    public TaskDto getTask(Long id) {
        if (taskRepository.existsById(id)) {
            return taskRepository.findById(id).map(TaskDto::toDto).get();
        }
        throw new NotFoundException("Task not found");

    }



//    public List<TaskDto> getAllTasks() {
//        List<Task> tasks = taskRepository.findAll();
//        return tasks.stream().map(TaskDto::toDto).collect(Collectors.toList());
//
//    }

    @Override
    public Page<TaskDto> getPaginatedTasks(int page, int size) {
        Page<Task> taskPage = taskRepository.findAll(PageRequest.of(page, size));
        return taskPage.map(TaskDto::toDto);
    }


    @Override
    public String deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task not found"));
       User creator = task.getCreator();
        creator.getCreatedTasks().remove(task);
        userRepository.save(creator);

        taskRepository.delete(task);

        return "Task deleted";
    }

    @Override
    public List<TaskDto> getTasksByCriteria(String title, String description, Status status, LocalDateTime dueDate , int page, int size) {

        Specification<Task> specification = TaskSpecification.getTasksByCriteria(title, description, status, dueDate);

        Pageable pageable = PageRequest.of(page, size); // Creating pageable with the page and size

        return taskRepository.findAll(specification)
                .stream().map(TaskDto::toDto)
                .collect(Collectors.toList());
    }
}
