package com.example.banquemisr.repository;

import com.example.banquemisr.model.Task;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface TaskRepository  extends JpaRepository<Task , Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Task t WHERE t.creator.id = :userId")
    void deleteByUserId(@Param("userId") Long userId);
}
