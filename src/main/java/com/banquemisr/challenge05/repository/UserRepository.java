package com.banquemisr.challenge05.repository;

import com.banquemisr.challenge05.model.Task;
import com.banquemisr.challenge05.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u.createdTasks from User u where u.id = :userId")
    List<Task> findTasksByUserId(@Param("userId") Long userId);

    @Query("SELECT u FROM User u WHERE u.email = :email")
    User findUserByEmail(@Param("email")String email);




}
