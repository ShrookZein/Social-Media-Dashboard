package com.spring.social.controller;

import com.spring.social.service.FacebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.social.facebook.api.EducationExperience;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
//@Controller
@RequestMapping("/")
public class DemoController {

    @Autowired
    FacebookService facebookService;

    @GetMapping("/createFacebookAuthorization")
    public String createFacebookAuthorization(){
        return facebookService.createFacebookAuthorizationURL();
    }
    @GetMapping("/facebook")
    public void createFacebookAccessToken(@RequestParam("code") String code){
        facebookService.createFacebookAccessToken(code);
    }
    @GetMapping("/getName")
    public String getNameResponse(){
        return facebookService.getName();
    }
//    @GetMapping("/getEducation")
//    public ResponseEntity<?> EducationExperience() throws IOException {
//        return facebookService.geteducation();
//    }
    @GetMapping("/getGender")
    public String  getGender(){
        return facebookService.getgender();
    }
    @GetMapping("/getFriends")
    public ResponseEntity<?>  getFriends(){
        return facebookService.getUserFriends();
    }
    @GetMapping("/getAllFeeds")
    public ResponseEntity<?> getPost(){
        return facebookService.getAllFeeds();
    }
//    @GetMapping("/getUserPageLikes")
//    public ResponseEntity<?> getUserPageLikes(){
//        return facebookService.getUserPageLikes();
//    }

}
