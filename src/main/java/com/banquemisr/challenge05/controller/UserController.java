package com.banquemisr.challenge05.controller;


import com.banquemisr.challenge05.model.dto.TaskDto;
import com.banquemisr.challenge05.model.dto.UserDto;
import com.banquemisr.challenge05.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {


    @Autowired
    private UserService userService;




    @GetMapping("/getTasksByUser/{id}")
    public ResponseEntity<List<TaskDto>> getTasksByUser(@PathVariable Long id) {
        try {
         return new ResponseEntity<>( userService.getAllTasksByUser(id), HttpStatus.OK)  ;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @GetMapping("/getUserById/{id}")
    @PreAuthorize("hasRole('ADMIN')") // Only users with the ROLE_ADMIN can access this method
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>( userService.getUser(id), HttpStatus.OK)  ;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    // Admin
    @GetMapping("/getAllUser")
    @PreAuthorize("hasRole('ADMIN')") // Only users with the ROLE_ADMIN can access this method
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
