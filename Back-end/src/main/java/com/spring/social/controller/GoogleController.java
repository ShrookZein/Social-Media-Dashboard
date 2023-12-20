package com.spring.social.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.spring.social.dto.TokenDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;

@RestController
@RequestMapping("/api")
//http://localhost:8080/api
@CrossOrigin("http://localhost:4200")
public class GoogleController {

    @Value("${google.id}")
    private String idClient;
    //http://localhost:8080/api/google
//    @PostMapping("/google")
//    public ResponseEntity<?> loginWithGoogle(@RequestBody TokenDto tokenDto) throws IOException {
//        NetHttpTransport transport=new NetHttpTransport() ;
//        JacksonFactory jsonFactory=JacksonFactory.getDefaultInstance();
//        GoogleIdTokenVerifier.Builder verify=new GoogleIdTokenVerifier.Builder(transport,jsonFactory)
//                .setAudience(Collections.singleton(idClient));
//        GoogleIdToken googleIdToken=GoogleIdToken.parse(verify.getJsonFactory(),tokenDto.getToken());
//        GoogleIdToken.Payload payload=googleIdToken.getPayload();
//        return new ResponseEntity<>(payload, HttpStatus.OK);
//    }
    @PostMapping("/facebook")
    public ResponseEntity<?> loginWithFacebook(@RequestBody TokenDto tokenDto) throws IOException {
        Facebook facebook=new FacebookTemplate(tokenDto.getToken());
        String[]data={"email","name","picture","education","gender","birthday","about","verified","religion","languages"};
        User user=facebook.fetchObject("me",User.class,data);

        return new ResponseEntity<>(user,HttpStatus.OK);
    }
}
