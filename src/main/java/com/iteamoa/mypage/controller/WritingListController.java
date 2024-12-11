package com.iteamoa.mypage.controller;

import com.iteamoa.mypage.dto.FeedDto;
import com.iteamoa.mypage.service.WritingListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("writing")
public class WritingListController {
    private final WritingListService writingListService;

//    @GetMapping
//    public ResponseEntity<?> getWritingList() {
//        List<FeedDto> FeedDTOs = writingListService.getWritingList();
//        return ResponseEntity.ok(FeedDTOs);
//    }

}
