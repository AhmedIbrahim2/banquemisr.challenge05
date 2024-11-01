package com.example.banquemisr.model.dto;

import com.example.banquemisr.model.History;
import com.example.banquemisr.model.Task;
import com.example.banquemisr.model.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.LocalDateTime;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class HistoryDto {


    private Long id;

    private Status oldStatus;

    private Status newStatus;

    private LocalDateTime changeDate;

    private Long Task_id;



    public static HistoryDto toDto(History history) {
        HistoryDto dto = new HistoryDto();
        dto.setId(history.getId());
        dto.setOldStatus(history.getOldStatus());
        dto.setNewStatus(history.getNewStatus());
        dto.setChangeDate(history.getChangeDate());
        dto.setTask_id(history.getTask().getId());
        return dto;
    }

    public static History toEntity(HistoryDto historyDto , Task task) {
        History history = new History();
        history.setId(historyDto.getId());
        history.setOldStatus(historyDto.getOldStatus());
        history.setNewStatus(historyDto.getNewStatus());
        history.setChangeDate(historyDto.getChangeDate());
        history.setTask(task);
        return history;
    }
}