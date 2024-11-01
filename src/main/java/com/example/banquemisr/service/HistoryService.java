package com.example.banquemisr.service;

import com.example.banquemisr.model.dto.HistoryDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface HistoryService {

    public Page<HistoryDto> getAllHistoryByTaskId(Long id, int page, int size);
    // i got all history for this task id



   // List<HistoryDto> getAllHistoryByUserId(Long id);
    // i got all tasks related to this user then i got all history for this tasks that's done

    public Page<HistoryDto> getAllHistoryByUserId(Long id, int page, int size) ;


    }
