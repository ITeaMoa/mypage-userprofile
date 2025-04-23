package com.iteamoa.mypage.service;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.model.S3Exception;

@Service
public class S3Service {

    private final S3Client s3Client;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    public S3Service(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public String uploadFile(String userId, MultipartFile file) throws IOException {
        // System.out.println(" [S3 업로드 진입] userId: " + userId);
        // System.out.println(" [파일 객체 상태] file == null? " + (file == null));
        // System.out.println(" [파일 비어있음?] file.isEmpty()? " + (file != null && file.isEmpty()));

        String uniqueFileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        String keyName = "avatars/" + userId + "/" + uniqueFileName;
    
        System.out.println(" S3 업로드 시작 - 파일명: " + keyName);
    
        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(keyName)
                    .contentType(file.getContentType())  
                    .build();
    
            s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
    
            String uploadedUrl = "https://" + bucketName + ".s3.ap-northeast-2.amazonaws.com/" + keyName;
            System.out.println(" S3 업로드 성공 URL: " + uploadedUrl);
    
            return uploadedUrl;
        } catch (S3Exception e) {
            System.out.println(" S3 업로드 실패: " + e.awsErrorDetails().errorMessage());
            throw new RuntimeException(" S3 업로드 실패: " + e.awsErrorDetails().errorMessage());
        }
    }
    
    
}
