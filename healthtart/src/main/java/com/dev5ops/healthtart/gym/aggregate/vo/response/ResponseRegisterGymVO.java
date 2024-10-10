package com.dev5ops.healthtart.gym.aggregate.vo.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ResponseRegisterGymVO {
    private Long gymCode;
    private String gymName;
    private String address;
    private String businessNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
