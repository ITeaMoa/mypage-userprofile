package com.iteamoa.mypage.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.iteamoa.mypage.constant.DynamoDbEntityType;
import com.iteamoa.mypage.entity.UserProfileEntity;
import com.iteamoa.mypage.repository.UserProfileRepository;


@Service
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;

    public UserProfileService(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

     public UserProfileEntity updateUserProfile(String userId, UserProfileEntity updatedProfile) {
        Optional<UserProfileEntity> existingProfileOpt = userProfileRepository.findByUserId(userId);
        
        UserProfileEntity existingProfile;
        if (existingProfileOpt.isEmpty()) {
            // 프로필이 없을 경우 새로 생성
            existingProfile = new UserProfileEntity();
            existingProfile.setPk("USER#" + userId);
            existingProfile.setSk("PROFILE#");
            existingProfile.setEntityType(DynamoDbEntityType.USER);
            existingProfile.setTimestamp(LocalDateTime.now());
        } else {
            existingProfile = existingProfileOpt.get();
        }
        
        // 필드 업데이트
        // if (updatedProfile.getName() != null) existingProfile.setName(updatedProfile.getName());
        if (updatedProfile.getAvatarUrl() != null) existingProfile.setAvatarUrl(updatedProfile.getAvatarUrl());
        if (updatedProfile.getHeadLine() != null) existingProfile.setHeadLine(updatedProfile.getHeadLine());
        if (updatedProfile.getTags() != null) existingProfile.setTags(updatedProfile.getTags());
        if (updatedProfile.getEducations() != null) existingProfile.setEducations(updatedProfile.getEducations());
        if (updatedProfile.getPersonalUrl() != null) existingProfile.setPersonalUrl(updatedProfile.getPersonalUrl());
        if (updatedProfile.getExperiences() != null) existingProfile.setExperiences(updatedProfile.getExperiences());
        if (updatedProfile.getUserStatus() != null) existingProfile.setUserStatus(updatedProfile.getUserStatus());

        // 프로필 업데이트
        return userProfileRepository.updateProfile(existingProfile);
    }


    //유저프로필 정보가 없으면 바로 기본정보 생성
    public UserProfileEntity getOrCreateUserProfile(String userId) {
        // PROFILE# 조회
        Optional<UserProfileEntity> userProfileOpt = userProfileRepository.findByUserIdAndType(userId, "PROFILE#");
    
        if (userProfileOpt.isPresent()) {
            // 프로필이 존재하면 반환
            return userProfileOpt.get();
        }
    
        // INFO# 조회를 프로필로 바꿈꿈
        Optional<UserProfileEntity> userInfoOpt = userProfileRepository.findByUserIdAndType(userId, "PROFILE#");
        if (userInfoOpt.isEmpty()) {
            throw new IllegalArgumentException("User profile not found for userId: " + userId);
        }
    
        UserProfileEntity userInfo = userInfoOpt.get();
    
        //INFO# 데이터를 기반으로 PROFILE# 생성
        UserProfileEntity newProfile = new UserProfileEntity();
        newProfile.setPk(userInfo.getPk()); 
        newProfile.setEntityType(DynamoDbEntityType.USER);
        newProfile.setTimestamp(LocalDateTime.now());
        newProfile.setUserStatus(true);
    
        // 새 프로필 저장
        userProfileRepository.saveProfile(newProfile);
    
        return newProfile;
    }

}
