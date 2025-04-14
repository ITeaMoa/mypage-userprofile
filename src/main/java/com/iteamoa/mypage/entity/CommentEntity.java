package com.iteamoa.mypage.entity;

import java.time.LocalDateTime;

import com.iteamoa.mypage.constant.DynamoDbEntityType;
import com.iteamoa.mypage.converter.LocalDateTimeConverter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbConvertedBy;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamoDbBean
public class CommentEntity extends BaseEntity {

    private String commentId; // 댓글 고유 식별자
    private String userId;
    private String comment;
    private String nickname;

    @DynamoDbAttribute("entityType")
    public DynamoDbEntityType getEntityType() {
        return DynamoDbEntityType.COMMENT;
    }

    @DynamoDbAttribute("commentId")
    public String getCommentId() {
        return commentId;
    }

    @DynamoDbAttribute("userId")
    public String getUserId() {
        return userId;
    }

    @DynamoDbAttribute("comment")
    public String getComment() {
        return comment;
    }

    @DynamoDbAttribute("nickname")
    public String getNickname() {
        return nickname;
    }

    @Override
    @DynamoDbConvertedBy(LocalDateTimeConverter.class)
    @DynamoDbAttribute("timestamp")
    public LocalDateTime getTimestamp() {
        return super.getTimestamp();
    }
}