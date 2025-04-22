package com.iteamoa.mypage.repository;

import com.iteamoa.mypage.entity.CommentEntity;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.*;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.UpdateItemRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CommentRepository {
    private final DynamoDbEnhancedClient enhancedClient;
    private final DynamoDbClient dynamoDbClient;

    public CommentRepository(DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
        this.enhancedClient = DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();
    }

    private DynamoDbTable<CommentEntity> getTable() {
        return enhancedClient.table("IM_MAIN_TB", TableSchema.fromBean(CommentEntity.class));
    }

    private DynamoDbIndex<CommentEntity> getCreatorIdIndex() {
        return getTable().index("CreatorId-index");
    }

    public List<CommentEntity> findAllByCreatorId(String creatorId) {
        List<CommentEntity> results = new ArrayList<>();
        QueryConditional query = QueryConditional.keyEqualTo(Key.builder().partitionValue(creatorId).build());
        getCreatorIdIndex().query(r -> r.queryConditional(query))
                .forEach(page -> results.addAll(page.items()));
        return results;
    }

    public void updateUserStatusOnly(String pk, String sk, boolean newStatus) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("Pk", AttributeValue.builder().s(pk).build());
        key.put("Sk", AttributeValue.builder().s(sk).build());

        Map<String, AttributeValue> updatedValues = new HashMap<>();
        updatedValues.put(":newStatus", AttributeValue.builder().bool(newStatus).build());

        UpdateItemRequest request = UpdateItemRequest.builder()
                .tableName("IM_MAIN_TB")
                .key(key)
                .updateExpression("SET userStatus = :newStatus")
                .expressionAttributeValues(updatedValues)
                .build();

        dynamoDbClient.updateItem(request);
    }
}