package com.iteamoa.mypage.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.iteamoa.mypage.service.WithdrawService;

@RestController
@RequestMapping("/my/profile")
public class WithdrawController {

    private final WithdrawService withdrawService;

    public WithdrawController(WithdrawService withdrawService) {
        this.withdrawService = withdrawService;
    }

    @PutMapping("/withdraw/{userId}")
    public ResponseEntity<String> withdrawUser(@PathVariable("userId") String userId) {
        withdrawService.withdrawUser(userId);
        return ResponseEntity.ok("탈퇴 처리가 완료되었습니다.");
    }
}
