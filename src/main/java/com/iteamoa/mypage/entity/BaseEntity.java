package com.iteamoa.mypage.entity;


import com.iteamoa.mypage.constant.DynamoDbEntityType;
import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

import java.time.LocalDateTime;

@Setter
public abstract class BaseEntity {
    private String pk;
    private String sk;
    private DynamoDbEntityType entityType;
    private LocalDateTime timestamp;

    public BaseEntity() {}

    @DynamoDbPartitionKey
    @DynamoDbAttribute("Pk")
    public String getPk() {
        return pk;
    }

    @DynamoDbSortKey
    @DynamoDbAttribute("Sk")
    @DynamoDbSecondaryPartitionKey(indexNames = {"MostLikedFeed-index", "PostedFeed-index"})
    public String getSk() {
        return sk;
    }

    @DynamoDbAttribute("entityType")
    @DynamoDbSecondarySortKey(indexNames = "Like-index")
    public DynamoDbEntityType getEntityType(){
        return entityType;
    }

    @DynamoDbAttribute("timestamp")
    @DynamoDbSecondarySortKey(indexNames = "PostedFeed-index")
    public LocalDateTime getTimestamp(){
        return timestamp;
    }
}
