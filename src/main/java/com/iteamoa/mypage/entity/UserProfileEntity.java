package com.iteamoa.mypage.entity;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

import java.util.List;

@DynamoDbBean
public class UserProfileEntity extends BaseEntity {

    private String avatarUrl;
    private String headLine;
    private List<String> tags;
    private List<String> educations;
    private List<String> personalUrl;
    private List<String> experiences;

    public UserProfileEntity() {}

   

    @DynamoDbAttribute("avatarUrl")
    public String getAvatarUrl() {
        return avatarUrl;
    }

    @DynamoDbAttribute("headLine")
    public String getHeadLine() {
        return headLine;
    }

    @DynamoDbAttribute("tags")
    public List<String> getTags() {
        return tags;
    }

    @DynamoDbAttribute("educations")
    public List<String> getEducations() {
        return educations;
    }

    @DynamoDbAttribute("personalUrl")
    public List<String> getPersonalUrl() {
        return personalUrl;
    }

    @DynamoDbAttribute("experiences")
    public List<String> getExperiences() {
        return experiences;
    }


    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setHeadLine(String headLine) {
        this.headLine = headLine;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void setEducations(List<String> educations) {
        this.educations = educations;
    }

    public void setPersonalUrl(List<String> personalUrl) {
        this.personalUrl = personalUrl;
    }

    public void setExperiences(List<String> experiences) {
        this.experiences = experiences;
    }
}
