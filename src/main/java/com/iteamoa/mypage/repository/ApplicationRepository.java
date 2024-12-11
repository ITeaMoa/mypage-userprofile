package com.iteamoa.mypage.repository;

import com.iteamoa.mypage.entity.ApplicationEntity;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Repository
public class ApplicationRepository {
    private final DynamoDbTable<ApplicationEntity> applicationRepository;

    public ApplicationRepository(DynamoDbClient dynamoDbClient) {
        DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();
        this.applicationRepository = enhancedClient.table("IM_MAIN_TB", TableSchema.fromBean(ApplicationEntity.class));
    }

}
