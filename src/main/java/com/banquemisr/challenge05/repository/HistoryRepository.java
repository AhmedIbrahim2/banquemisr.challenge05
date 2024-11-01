package com.banquemisr.challenge05.repository;

import com.banquemisr.challenge05.model.History;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
    Page<History> findByTaskId(Long taskId, Pageable pageable);
}
