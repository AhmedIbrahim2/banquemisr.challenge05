package com.banquemisr.challenge05.service;


import com.banquemisr.challenge05.exception.NotFoundException;
import com.banquemisr.challenge05.model.Task;
import com.banquemisr.challenge05.model.User;
import com.banquemisr.challenge05.model.dto.TaskDto;
import com.banquemisr.challenge05.model.dto.UserDto;
import com.banquemisr.challenge05.repository.HistoryRepository;
import com.banquemisr.challenge05.repository.TaskRepository;
import com.banquemisr.challenge05.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private HistoryRepository historyRepository;




    public UserDto createUser(UserDto userDto) {
       User user = userRepository.save(UserDto.toEntity(userDto));
         return UserDto.toDto(user);
    }

    @Override
    public String updateUser(UserDto userDto , Long id) {

        User user = userRepository.findById(id).get();

        User user1 = userRepository.findUserByEmail(userDto.getEmail());
        if (user1 != null && !Objects.equals(user1.getEmail(), userDto.getEmail())) {
            throw new NotFoundException("email found , choose another one");
        }
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        userRepository.save(user);
        return "User updated Successfully";
//        BeanUtils.copyProperties(userDto, user,"id");
//        User user = userRepository.save(UserDto.toEntity(userDto));
    }

    @Override
    public UserDto getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task not found"));
        return UserDto.toDto(user);

    }

//    @Override
//    public List<UserDto> getAllUsers() {
//        List<User> users = userRepository.findAll();
//        return users
//                .stream().map(UserDto::toDto).collect(Collectors.toList());
//    }

    @Override
    public String deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task not found"));

        taskRepository.deleteByUserId(id);


        userRepository.delete(user);
        return "User deleted";
    }

    @Override
    public List<TaskDto> getAllTasksByUser(Long id) {
        List<Task> tasks = userRepository.findTasksByUserId(id);

        return tasks
                .stream().map(TaskDto::toDto).collect(Collectors.toList());
    }

    @Override
    public Page<UserDto> getPaginatedUsers(int page, int size) {
        Page<User> userPage = userRepository.findAll(PageRequest.of(page, size));
        return userPage.map(UserDto::toDto);
    }

}
