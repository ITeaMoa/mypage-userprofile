package com.iteamoa.mypage.service;

import com.iteamoa.mypage.entity.ApplicationEntity;
import com.iteamoa.mypage.entity.FeedEntity;
import com.iteamoa.mypage.entity.LikeEntity;
import com.iteamoa.mypage.entity.ReplyEntity;
import com.iteamoa.mypage.repository.ApplicationRepository;
import com.iteamoa.mypage.repository.FeedRepository;
import com.iteamoa.mypage.repository.LikeRepository;
import com.iteamoa.mypage.repository.ReplyRepository;
import com.iteamoa.mypage.repository.UserProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WithdrawService {

    private final UserProfileRepository userProfileRepository;
    private final FeedRepository feedRepository;
    private final ApplicationRepository applicationRepository;
    private final LikeRepository likeRepository;
    private final ReplyRepository replyRepository; 

    public WithdrawService(UserProfileRepository userProfileRepository,
                           FeedRepository feedRepository,
                           ApplicationRepository applicationRepository,
                           LikeRepository likeRepository,
                           ReplyRepository replyRepository) { 
        this.userProfileRepository = userProfileRepository;
        this.feedRepository = feedRepository;
        this.applicationRepository = applicationRepository;
        this.likeRepository = likeRepository;
        this.replyRepository = replyRepository;
    }

    public void withdrawUser(String userId) {
        String creatorId = "USER#" + userId;

        // 프로필 userStatus false
        userProfileRepository.findByUserIdAndType(userId, "PROFILE#")
            .ifPresent(profile -> {
                profile.setUserStatus(false);
                userProfileRepository.updateProfile(profile);
            });

        //피드
        List<FeedEntity> feeds = feedRepository.findAllByCreatorId(creatorId);
        for (FeedEntity feed : feeds) {
            feedRepository.updateUserStatusOnly(feed.getPk(), feed.getSk(), false);
        }

        //어플리케이션 
        List<ApplicationEntity> applications = applicationRepository.findAllByCreatorId(creatorId);
        for (ApplicationEntity app : applications) {
            applicationRepository.updateUserStatusOnly(app.getPk(), app.getSk(), false);
        }

        List<LikeEntity> likes = likeRepository.findAllByCreatorId(creatorId);
        for (LikeEntity like : likes) {
            likeRepository.updateUserStatusOnly(like.getPk(), like.getSk(), false);
        }

     // 대댓글 
        List<ReplyEntity> replies = replyRepository.findAllByCreatorId(creatorId);
        for (ReplyEntity reply : replies) {
            replyRepository.updateUserStatusOnly(reply.getPk(), reply.getSk(), false);
        }

    }
}
