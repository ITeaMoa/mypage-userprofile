package com.iteamoa.mypage.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iteamoa.mypage.entity.UserProfileEntity;
import com.iteamoa.mypage.service.S3Service;
import com.iteamoa.mypage.service.UserProfileService;

@RestController
@RequestMapping("/my/profile")
public class UserProfileController {

    private final UserProfileService userProfileService;
    private final S3Service s3Service;
    private final ObjectMapper objectMapper;

    @Autowired
    public UserProfileController(UserProfileService userProfileService, S3Service s3Service, ObjectMapper objectMapper) {
        this.userProfileService = userProfileService;
        this.s3Service = s3Service;
        this.objectMapper = objectMapper;
    }

    @PutMapping(value = "/{userId}", consumes = "multipart/form-data")
public ResponseEntity<?> updateUserProfile(
        @PathVariable("userId") String userId,
        @RequestPart(value = "file", required = false) MultipartFile file,
        @RequestPart("profile") String profileJson) throws IOException {
    
    System.out.println("✅ 프로필 업데이트 시작 - userId: " + userId);
    System.out.println("✅ profileJson 데이터: " + profileJson);

    UserProfileEntity updatedProfile = objectMapper.readValue(profileJson, UserProfileEntity.class);

    if (file != null && !file.isEmpty()) {
        System.out.println("✅ 파일 업로드 준비 - 파일명: " + file.getOriginalFilename());

        String avatarUrl = s3Service.uploadFile(userId, file);
        System.out.println("✅ S3 업로드 성공 URL: " + avatarUrl);
        updatedProfile.setAvatarUrl(avatarUrl);
    } else {
        System.out.println("❗ 파일이 전달되지 않았습니다.");
    }

    UserProfileEntity updatedEntity = userProfileService.updateUserProfile(userId, updatedProfile);
    return ResponseEntity.ok(updatedEntity);
}






    @GetMapping("/{userId}")
    public ResponseEntity<UserProfileEntity> getOrCreateUserProfile(@PathVariable("userId") String userId) {
        UserProfileEntity userProfile = userProfileService.getOrCreateUserProfile(userId);
        return ResponseEntity.ok(userProfile);
    }

    @GetMapping("/test")
    public ResponseEntity<String> testEndpoint() {
        return ResponseEntity.ok("hello mypage");
    }

    // @PostMapping(value = "/test-upload", consumes = "multipart/form-data")
    // public ResponseEntity<String> testUploadFile(@RequestPart("file") MultipartFile file) {
    //     if (file != null && !file.isEmpty()) {
    //         System.out.println("✅ 테스트 파일 업로드 - 파일명: " + file.getOriginalFilename());
    //         return ResponseEntity.ok("파일 업로드 성공: " + file.getOriginalFilename());
    //     } else {
    //         return ResponseEntity.badRequest().body("❌ 파일이 전달되지 않았습니다.");
    //     }
    // }
    

}
