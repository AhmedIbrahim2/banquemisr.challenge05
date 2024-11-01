package com.example.banquemisr.controller;


import com.example.banquemisr.model.dto.TaskDto;
import com.example.banquemisr.model.dto.UserDto;
import com.example.banquemisr.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {


    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {
        try {
            return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);

        } catch (Exception e) {
            throw new RuntimeException("Error while adding user", e);
        }
    }



    @GetMapping("/getTasksByUser/{id}")
    public ResponseEntity<List<TaskDto>> getTasksByUser(@PathVariable Long id) {
        try {
         return new ResponseEntity<>( userService.getAllTasksByUser(id), HttpStatus.OK)  ;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @GetMapping("/getUserById/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>( userService.getUser(id), HttpStatus.OK)  ;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @GetMapping("/getAllUser")
    public ResponseEntity<Page<UserDto>> getAllUser(@RequestParam(required = false) Integer page,
                                                    @RequestParam(defaultValue = "10") int size  ) {
        try {
            return new ResponseEntity<>( userService.getPaginatedUsers(page,size), HttpStatus.OK)  ;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        try {
           return new ResponseEntity<>( userService.deleteUser(id),HttpStatus.OK) ;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/editUser/{id}")
    public ResponseEntity<String> editUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        try {
            return new ResponseEntity<>(userService.updateUser(userDto,id),HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
