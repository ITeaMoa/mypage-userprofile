package com.iteamoa.mypage.repository;

import com.iteamoa.mypage.entity.UserProfileEntity;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.UpdateItemEnhancedRequest;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Repository
public class UserProfileRepository {
    private final DynamoDbTable<UserProfileEntity> userProfileTable;

    public UserProfileRepository(DynamoDbClient dynamoDbClient) {
        DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();
        this.userProfileTable = enhancedClient.table("IM_MAIN_TB", TableSchema.fromBean(UserProfileEntity.class));
    }

    // 사용자 프로필 조회 함수
    public Optional<UserProfileEntity> findByUserId(String userId) {
        Key key = Key.builder()
                .partitionValue("USER#" + userId)
                .sortValue("PROFILE#")
                .build();
        UserProfileEntity profile = userProfileTable.getItem(key);
        return Optional.ofNullable(profile);
    }

    // 사용자 프로필 업데이트 함수
    public UserProfileEntity updateProfile(UserProfileEntity updatedProfile) {
        updatedProfile.setTimestamp(java.time.LocalDateTime.now());
        userProfileTable.updateItem(UpdateItemEnhancedRequest.builder(UserProfileEntity.class)
                .item(updatedProfile)
                .build());
        return updatedProfile;
    }

    public Optional<UserProfileEntity> findByUserIdAndType(String userId, String skType) {
        Key key = Key.builder()
                .partitionValue("USER#" + userId)
                .sortValue(skType)
                .build();
        UserProfileEntity profile = userProfileTable.getItem(key);
        return Optional.ofNullable(profile);
    }
    
    public void saveProfile(UserProfileEntity userProfile) {
        userProfileTable.putItem(userProfile);
    }
}
