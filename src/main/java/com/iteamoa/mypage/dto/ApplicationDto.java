package com.iteamoa.mypage.dto;

import com.iteamoa.mypage.constant.StatusType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationDto {
    private String pk;
    private String sk;
    private String entityType;
    private String part;
    private StatusType status;
    private String feedType;
    private LocalDateTime timestamp;
}
