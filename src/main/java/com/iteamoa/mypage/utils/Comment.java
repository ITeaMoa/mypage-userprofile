package com.iteamoa.mypage.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
public class Comment {
    private String userId;
    private String comment;
    private LocalDateTime timestamp;
}
