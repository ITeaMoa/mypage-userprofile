package com.iteamoa.mypage.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iteamoa.mypage.entity.UserProfileEntity;
import com.iteamoa.mypage.service.S3Service;
import com.iteamoa.mypage.service.UserProfileService;

@RestController
public class UserProfileController {
    private final UserProfileService userProfileService;
    private final S3Service s3Service;

    public UserProfileController(UserProfileService userProfileService, S3Service s3Service) {
        this.userProfileService = userProfileService;
        this.s3Service = s3Service;
    }
    
    @PutMapping("/{userId}")
public ResponseEntity<UserProfileEntity> updateUserProfile(
        @PathVariable("userId") String userId,
        @RequestPart(value = "file", required = false) MultipartFile file,
        @RequestPart("profile") String profileJson) throws IOException {

    // JSON 데이터를 UserProfileEntity로 변환
    ObjectMapper objectMapper = new ObjectMapper();
    UserProfileEntity updatedProfile = objectMapper.readValue(profileJson, UserProfileEntity.class);

  
    if (file != null && !file.isEmpty()) {
        String avatarUrl = s3Service.uploadFile(userId, file);
        updatedProfile.setAvatarUrl(avatarUrl);
    }

    // 프로필 업데이트
    UserProfileEntity updatedEntity = userProfileService.updateUserProfile(userId, updatedProfile);
    return ResponseEntity.ok(updatedEntity);
}


    

    
    @GetMapping("/{userId}")
    public ResponseEntity<UserProfileEntity> getOrCreateUserProfile(@PathVariable("userId") String userId) {
    UserProfileEntity userProfile = userProfileService.getOrCreateUserProfile(userId);
    return ResponseEntity.ok(userProfile);
}
}
