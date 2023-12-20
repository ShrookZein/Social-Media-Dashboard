package com.spring.social.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.social.facebook.api.*;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FacebookService {

    @Value("${spring.social.facebook.appId}")
    String facebookAppId;
    @Value("${spring.social.facebook.appSecret}")
    String facebookSecret;
    String accessToken;

    public String createFacebookAuthorizationURL(){
        FacebookConnectionFactory connectionFactory = new FacebookConnectionFactory(facebookAppId, facebookSecret);
        OAuth2Operations oauthOperations = connectionFactory.getOAuthOperations();
        OAuth2Parameters params = new OAuth2Parameters();
        params.setRedirectUri("http://localhost:8080/facebook");
        params.setScope("public_profile,email,user_birthday,user_gender,user_age_range,user_friends,user_videos,user_posts,user_photos,user_location,user_link,user_likes,user_gender");
        return oauthOperations.buildAuthorizeUrl(params);
    }
    public void createFacebookAccessToken(String code) {
        FacebookConnectionFactory connectionFactory = new FacebookConnectionFactory(facebookAppId, facebookSecret);
        AccessGrant accessGrant = connectionFactory.getOAuthOperations().exchangeForAccess(code, "http://localhost:8080/facebook", null);
        accessToken = accessGrant.getAccessToken();
        System.out.println(accessToken);
    }
    public String getName() {
        Facebook facebook = new FacebookTemplate(accessToken);
        String[] fields = {"id", "name"};
        User user=facebook.fetchObject("me", User.class, fields);
        return user.getName();
    }

    public ResponseEntity<?> geteducation() throws IOException {
        Facebook facebook = new FacebookTemplate(accessToken);
        String[] fields = {"id", "name","picture","education","gender","birthday"};
        List<EducationExperience> user=facebook.userOperations().getUserProfile().getEducation();
//        String ss=user.getEducation().get(0).toString();
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    public String getgender() {
        Facebook facebook = new FacebookTemplate(accessToken);
        String[] fields = {"id", "name","picture","education","gender","birthday"};
        User user=facebook.fetchObject("me", User.class, fields);
        System.out.println(user.getGender());
        List<String> friendIds = facebook.friendOperations().getFriendIds();
        return user.getGender();
    }

    public ResponseEntity<?> getUserFriends() {
        Facebook facebook = new FacebookTemplate(accessToken);
        String[] fields = {"id", "name","picture","education","gender","birthday","likes"};
        User user=facebook.fetchObject("me", User.class, fields);
        System.out.println(user.getGender());
        PagedList<Reference> friendList = facebook.friendOperations().getFriends();
        return new ResponseEntity<>(friendList,HttpStatus.OK);
    }
    public ResponseEntity<?> getAllFeeds() {
        Facebook facebook = new FacebookTemplate(accessToken);
        String[] fields = {"id", "name","picture","education","gender","birthday","likes"};
        User user=facebook.fetchObject("me", User.class, fields);
        System.out.println(user.getGender());
//        List<String> friendIds = facebook.;
        PagedList<Post> posts= facebook.feedOperations().getFeed();
//        List<String>messages=new ArrayList<>();
//        for(int i=0;i<posts.size();i++){
//            messages.add(posts.get(i).getMessage());
//        }
//        return messages;
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }
//    public ResponseEntity<?> getUserPageLikes() {
//        Facebook facebook = new FacebookTemplate(accessToken);
//        String[] fields = {"id", "name","picture","education","gender","birthday","likes"};
//        User user=facebook.fetchObject("me", User.class, fields);
//        System.out.println(user.getGender());
//        PagedList<Page> pageLikes= facebook.likeOperations().getPagesLiked();
//        return new ResponseEntity<>(pageLikes,HttpStatus.OK);
//    }

        public ResponseEntity<?> getUserPageLikes() {
        Facebook facebook = new FacebookTemplate(accessToken);
        String[] fields = {"id", "name","picture","education","gender","birthday","likes"};
        User user=facebook.fetchObject("me", User.class, fields);
        System.out.println(user.getGender());
        PagedList<Reference> pageLikes= facebook.likeOperations().getLikes("100014225927039");
        return new ResponseEntity<>(pageLikes,HttpStatus.OK);
    }

}