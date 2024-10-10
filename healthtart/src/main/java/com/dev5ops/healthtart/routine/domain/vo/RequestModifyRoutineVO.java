package com.dev5ops.healthtart.routine.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RequestModifyRoutineVO {
    private String title;
    private Integer time;
    private LocalDateTime updatedAt;

}
