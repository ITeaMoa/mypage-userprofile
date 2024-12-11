package com.iteamoa.mypage.constant;

import lombok.Getter;

@Getter
public enum StatusType {
    PENDING("Pending"),
    ACCEPTED("Accepted"),
    REJECTED("Rejected");

    private final String status;

    StatusType(String status) {
        this.status = status;
    }

}
