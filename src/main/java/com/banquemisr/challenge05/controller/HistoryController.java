package com.example.banquemisr.controller;


import com.example.banquemisr.model.dto.HistoryDto;
import com.example.banquemisr.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/history")
public class HistoryController {

    @Autowired
    private HistoryService historyService;



    @GetMapping("/getHistoryByTaskId/{id}")
    public ResponseEntity<Page<HistoryDto>> getHistoryByTaskId(@PathVariable Long id , @RequestParam int page , @RequestParam int size) {
        try {
            return new ResponseEntity<>(historyService.getAllHistoryByTaskId(id , page , size), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    @GetMapping("/getHistoryByUserId/{id}")
    public ResponseEntity<Page<HistoryDto>> getHistoryByUserId(@PathVariable Long id , @RequestParam int page , @RequestParam int size ) {
        try {
            return new ResponseEntity<>(historyService.getAllHistoryByUserId(id , page , size), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
