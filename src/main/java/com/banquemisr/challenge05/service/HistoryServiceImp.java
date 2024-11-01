package com.banquemisr.challenge05.service;

import com.banquemisr.challenge05.model.History;
import com.banquemisr.challenge05.model.Task;
import com.banquemisr.challenge05.model.dto.HistoryDto;
import com.banquemisr.challenge05.repository.HistoryRepository;
import com.banquemisr.challenge05.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class HistoryServiceImp  implements HistoryService{

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<HistoryDto> getAllHistoryByTaskId(Long id, int page, int size) {
     //   Page<History> pagedHistory = historyRepository.findAll(PageRequest.of(page , size));
      //  List<History> histories = historyRepository.findByTaskId(id);

        Page<History> histories = historyRepository.findByTaskId(id, PageRequest.of(page, size));

//        return histories
//                .stream().map(HistoryDto::toDto).collect(Collectors.toList());

        return histories.map(HistoryDto::toDto);

    }

    @Override
    public Page<HistoryDto> getAllHistoryByUserId(Long id, int page, int size) {
        List<Task> taskDtoList = userRepository.findTasksByUserId(id);

        List<History> allHistories = taskDtoList.stream()
                .flatMap(task -> historyRepository.findByTaskId(task.getId(), PageRequest.of(0, Integer.MAX_VALUE)).getContent().stream())
                .toList();

        int start = Math.toIntExact((long) page * size);
        int end = Math.min(start + size, allHistories.size());

        List<HistoryDto> historyDtos = allHistories.subList(start, end).stream()
                .map(HistoryDto::toDto)
                .collect(Collectors.toList());


        return new PageImpl<>(historyDtos, PageRequest.of(page, size), allHistories.size());

    }

}
