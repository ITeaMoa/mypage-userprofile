package com.iteamoa.mypage.entity;

import com.iteamoa.mypage.constant.DynamoDbEntityType;
import com.iteamoa.mypage.utils.Comment;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSecondarySortKey;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@DynamoDbBean
public class FeedEntity extends BaseEntity {
    private DynamoDbEntityType entityType;
    private String creatorId;
    private String title;
    private int recruitmentNum; //승인되면 올라가는거
    private LocalDateTime deadline;
    private String place;
    private int period;
    private List<String> tags;
    private int likesCount;
    private String content;
    private List<Comment> comments;
    private boolean postStatus;
    private boolean savedFeed;
    private Map<String, Integer> roles; //
    private Map<String, Integer> recruitmentRoles; //신청하면 바로바로 올라가는거

    public FeedEntity() {}

    @DynamoDbAttribute("creatorId")
    public String getCreatorId(){
        return creatorId;
    }

    @DynamoDbAttribute("title")
    public String getTitle(){
        return title;
    }

    @DynamoDbAttribute("recruitmentNum")
    public int getRecruitmentNum(){
        return recruitmentNum;
    }

    @DynamoDbAttribute("deadline")
    public LocalDateTime getDeadline(){
        return deadline;
    }

    @DynamoDbAttribute("place")
    public String getPlace(){
        return place;
    }

    @DynamoDbAttribute("period")
    public int getPeriod(){
        return period;
    }

    @DynamoDbAttribute("tags")
    public List<String> getTags(){
        return tags;
    }

    @DynamoDbAttribute("likesCount")
    @DynamoDbSecondarySortKey(indexNames = "MostLikedFeed-Index")
    public int getLikesCount(){
        return likesCount;
    }

    @DynamoDbAttribute("content")
    public String getContent(){
        return content;
    }

    @DynamoDbAttribute("comments")
    public List<Comment> getComments(){
        return comments;
    }

    @DynamoDbAttribute("postStatus")
    public boolean getPostStatus(){
        return postStatus;
    }

    @DynamoDbAttribute("savedFeed")
    public boolean getSavedFeed(){
        return savedFeed;
    }

    @DynamoDbAttribute("roles")
    public Map<String, Integer> getRoles(){
        return roles;
    }

    @DynamoDbAttribute("recruitmentRoles")
    public Map<String, Integer> getRecruitmentRoles(){
        return recruitmentRoles;
    }

}
