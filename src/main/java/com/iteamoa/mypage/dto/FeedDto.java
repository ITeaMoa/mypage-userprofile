package com.iteamoa.mypage.dto;

import com.iteamoa.mypage.constant.DynamoDbEntityType;
import com.iteamoa.mypage.utils.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FeedDto {
    private String pk;
    private String sk;
    private DynamoDbEntityType entityType;
    private String creatorId;
    private String title;
    private int recruitmentNum;
    private LocalDateTime deadline;
    private String place;
    private int period;
    private List<String> tags;
    private int likesCount;
    private String content;
    private List<Comment> comments;
    private boolean postStatus;
    private LocalDateTime timestamp;
    private boolean savedFeed;
    private Map<String, Integer> applyRoles;
    private Map<String, Integer> recruitmentRoles;


}
