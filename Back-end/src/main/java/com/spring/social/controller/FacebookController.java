//package com.spring.social.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.social.connect.ConnectionRepository;
//import org.springframework.social.facebook.api.Facebook;
//import org.springframework.social.facebook.api.PagedList;
//import org.springframework.social.facebook.api.Post;
//import org.springframework.social.facebook.api.impl.FacebookTemplate;
//import org.springframework.social.facebook.connect.FacebookConnectionFactory;
//import org.springframework.social.oauth2.AccessGrant;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping("/")
//public class FacebookController {
////    @Autowired
//    private Facebook facebook;
//    String accessToken;
//    @Value("${spring.social.facebook.appId}")
//    String facebookAppId;
//    @Value("${spring.social.facebook.appSecret}")
//    String facebookSecret;
////    @Autowired
//    private ConnectionRepository connectionRepository;
//
//    public FacebookController(Facebook facebook, ConnectionRepository connectionRepository) {
//        this.facebook = facebook;
//        this.connectionRepository = connectionRepository;
//    }
//    @GetMapping("")
//    public String getAllfeeds(Model model){
////        FacebookConnectionFactory connectionFactory = new FacebookConnectionFactory(facebookAppId, facebookSecret);
////        AccessGrant accessGrant = connectionFactory.getOAuthOperations().exchangeForAccess(code, "http://localhost:8080/facebook", null);
////        accessToken = accessGrant.getAccessToken();
////        facebook=new FacebookTemplate(accessToken);
//        if(connectionRepository.getPrimaryConnection(Facebook.class)==null){
//            return "redirect:/connect/facebook";
//        }
//        PagedList<Post> posts= facebook.feedOperations().getFeed();
//        model.addAttribute("posts",posts);
//        model.addAttribute("profileName",posts.get(0).getFrom().getName());
//        return "profile";
//
//    }
//}
