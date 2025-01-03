package com.banquemisr.challenge05.model.dto;


import com.banquemisr.challenge05.model.Notification;
import com.banquemisr.challenge05.model.Task;
import com.banquemisr.challenge05.model.User;
import com.banquemisr.challenge05.model.enums.Role;
import lombok.*;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserDto {

    private Long id;
    private String username;
    private String email;
    private Role role;
    private List<Task> tasks; // Add a list of tasks
    private List<Notification> notifications;


    public static UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .tasks(user.getCreatedTasks())
                .notifications(user.getNotifications())
                .build();
    }



    public static User toEntity(UserDto dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setRole(dto.getRole());
        user.setCreatedTasks(dto.getTasks());
        user.setNotifications(dto.getNotifications());
        return user;
    }

}
