// ✅ ApplicationEntity.java
package com.iteamoa.mypage.entity;

import com.iteamoa.mypage.constant.DynamoDbEntityType;
import com.iteamoa.mypage.constant.StatusType;
import com.iteamoa.mypage.converter.LocalDateTimeConverter;
import lombok.*;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamoDbBean
public class ApplicationEntity extends BaseEntity {

    private String part;
    private StatusType status;

    @DynamoDbAttribute("entityType")
    public DynamoDbEntityType getEntityType() {
        return DynamoDbEntityType.APPLICATION;
    }

    @DynamoDbAttribute("part")
    public String getPart() {
        return part;
    }

    @DynamoDbAttribute("status")
    public StatusType getStatus() {
        return status;
    }

    @Override
    @DynamoDbConvertedBy(LocalDateTimeConverter.class)
    @DynamoDbAttribute("timestamp")
    public LocalDateTime getTimestamp() {
        return super.getTimestamp();
    }

    @Override
    @DynamoDbPartitionKey
    @DynamoDbAttribute("Pk")
    public String getPk() {
        return super.getPk();
    }

    @Override
    @DynamoDbSortKey
    @DynamoDbAttribute("Sk")
    public String getSk() {
        return super.getSk();
    }

    @Override
    @DynamoDbAttribute("creatorId")
    @DynamoDbSecondaryPartitionKey(indexNames = {"CreatorId-index"})
    public String getCreatorId() {
        return super.getCreatorId();
    }

    @Override
    @DynamoDbAttribute("userStatus")
    public Boolean getUserStatus() {
        return super.getUserStatus();
    }
}