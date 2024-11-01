package com.example.banquemisr.service;

import com.example.banquemisr.model.dto.TaskDto;
import com.example.banquemisr.model.dto.UserDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    public UserDto createUser(UserDto userDto);
    public String updateUser(UserDto userDto, Long id);
    public UserDto getUser(Long id);
//    public List<UserDto> getAllUsers();
    public String deleteUser(Long id);

    public List<TaskDto> getAllTasksByUser(Long id);

    Page<UserDto> getPaginatedUsers(int page, int size);


}
