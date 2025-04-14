// ✅ LikeEntity.java
package com.iteamoa.mypage.entity;

import com.iteamoa.mypage.constant.DynamoDbEntityType;
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
public class LikeEntity extends BaseEntity {

    private String feedID;
    private String feedType;

    @DynamoDbAttribute("entityType")
    public DynamoDbEntityType getEntityType() {
        return DynamoDbEntityType.LIKE;
    }

    @DynamoDbAttribute("feedID")
    public String getFeedID() {
        return feedID;
    }

    public void setFeedID(String feedID) {
        this.feedID = feedID;
    }

    @DynamoDbAttribute("feedType")
    public String getFeedType() {
        return feedType;
    }

    public void setFeedType(String feedType) {
        this.feedType = feedType;
    }

    @Override
    @DynamoDbPartitionKey
    @DynamoDbAttribute("Pk")
    public String getPk() {
        return super.getPk();
    }

    @Override
    public void setPk(String pk) {
        super.setPk(pk);
    }

    @Override
    @DynamoDbSortKey
    @DynamoDbAttribute("Sk")
    public String getSk() {
        return super.getSk();
    }

    @Override
    public void setSk(String sk) {
        super.setSk(sk);
    }

    @Override
    @DynamoDbAttribute("creatorId")
    @DynamoDbSecondaryPartitionKey(indexNames = {"CreatorId-index"})
    public String getCreatorId() {
        return super.getCreatorId();
    }

    @Override
    public void setCreatorId(String creatorId) {
        super.setCreatorId(creatorId);
    }

    @Override
    @DynamoDbAttribute("userStatus")
    public Boolean getUserStatus() {
        return super.getUserStatus();
    }

    @Override
    public void setUserStatus(Boolean userStatus) {
        super.setUserStatus(userStatus);
    }

    @Override
    @DynamoDbConvertedBy(LocalDateTimeConverter.class)
    @DynamoDbAttribute("timestamp")
    public LocalDateTime getTimestamp() {
        return super.getTimestamp();
    }

    @Override
    public void setTimestamp(LocalDateTime timestamp) {
        super.setTimestamp(timestamp);
    }
}
