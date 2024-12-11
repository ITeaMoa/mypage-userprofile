package com.iteamoa.mypage.repository;

import com.iteamoa.mypage.entity.FeedEntity;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Repository
public class FeedRepository {
    private final DynamoDbTable<FeedEntity> feedRepository;

    public FeedRepository(DynamoDbClient dynamoDbClient) {
        DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();
        this.feedRepository = enhancedClient.table("IM_MAIN_TB", TableSchema.fromBean(FeedEntity.class));
    }

}
