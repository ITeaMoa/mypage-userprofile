// ✅ FeedEntity.java
package com.iteamoa.mypage.entity;

import com.iteamoa.mypage.constant.DynamoDbEntityType;
import com.iteamoa.mypage.converter.CommentListConverter;
import com.iteamoa.mypage.converter.LocalDateTimeConverter;
import com.iteamoa.mypage.utils.Comment;
import lombok.*;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamoDbBean
public class FeedEntity extends BaseEntity {

    private String title;
    private String content;
    private Boolean postStatus;
    private Boolean savedFeed;
    private List<String> tags;
    private List<Comment> comments;
    private Integer recruitmentNum;
    private LocalDateTime deadline;
    private String place;
    private Integer period;
    private Integer likesCount;
    private Map<String, Integer> recruitmentRoles;
    private Map<String, Integer> roles;
    private String nickname;
    private String imageUrl;

    @DynamoDbAttribute("entityType")
    public DynamoDbEntityType getEntityType() {
        return DynamoDbEntityType.FEED;
    }

    @DynamoDbAttribute("title")
    public String getTitle() { return title; }

    @DynamoDbAttribute("content")
    public String getContent() { return content; }

    @DynamoDbAttribute("postStatus")
    public Boolean getPostStatus() { return postStatus; }

    @DynamoDbAttribute("savedFeed")
    public Boolean getSavedFeed() { return savedFeed; }

    @DynamoDbAttribute("tags")
    public List<String> getTags() { return tags; }

    @DynamoDbConvertedBy(CommentListConverter.class)
    @DynamoDbAttribute("comments")
    public List<Comment> getComments() { return comments; }

    @DynamoDbAttribute("recruitmentNum")
    public Integer getRecruitmentNum() { return recruitmentNum; }

    @DynamoDbConvertedBy(LocalDateTimeConverter.class)
    @DynamoDbAttribute("deadline")
    public LocalDateTime getDeadline() { return deadline; }

    @DynamoDbAttribute("place")
    public String getPlace() { return place; }

    @DynamoDbAttribute("period")
    public Integer getPeriod() { return period; }

    @DynamoDbSecondarySortKey(indexNames = "MostLikedFeed-index")
    @DynamoDbAttribute("likesCount")
    public Integer getLikesCount() { return likesCount; }

    @DynamoDbAttribute("roles")
    public Map<String, Integer> getRoles() { return roles; }

    @DynamoDbAttribute("recruitmentRoles")
    public Map<String, Integer> getRecruitmentRoles() { return recruitmentRoles; }

    @DynamoDbAttribute("nickname")
    public String getNickname() { return nickname; }

    @DynamoDbAttribute("imageUrl")
    public String getImageUrl() { return imageUrl; }
}
