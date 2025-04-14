package com.iteamoa.mypage.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iteamoa.mypage.utils.Comment;
import software.amazon.awssdk.enhanced.dynamodb.AttributeConverter;
import software.amazon.awssdk.enhanced.dynamodb.AttributeValueType;
import software.amazon.awssdk.enhanced.dynamodb.EnhancedType;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Collections;
import java.util.List;

public class CommentListConverter implements AttributeConverter<List<Comment>> {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public AttributeValue transformFrom(List<Comment> comments) {
        try {
            String json = mapper.writeValueAsString(comments);
            return AttributeValue.builder().s(json).build();
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert Comment list to JSON", e);
        }
    }

    @Override
    public List<Comment> transformTo(AttributeValue attributeValue) {
        try {
            String json = attributeValue.s();
            return mapper.readValue(json, new TypeReference<List<Comment>>() {});
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    @Override
    public AttributeValueType attributeValueType() {
        return AttributeValueType.S;
    }

    @Override
    public EnhancedType<List<Comment>> type() {
        return EnhancedType.listOf(Comment.class);
    }
}
