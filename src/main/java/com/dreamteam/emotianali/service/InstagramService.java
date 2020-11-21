package com.dreamteam.emotianali.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class InstagramService {
    private final String INSTAGRAM_ID = System.getenv("INSTAGRAM_ID");
    private final String INSTAGRAM_CLIENT_SECRET = System.getenv("INSTAGRAM_CLIENT_SECRET");
    private final String INSTAGRAM_REDIRECT_URI = System.getenv("INSTAGRAM_REDIRECT_URI");

    public String getCode() {
        return "https://api.instagram.com/oauth/authorize?" +
                "client_id=" + INSTAGRAM_ID +
                "&redirect_uri=" + INSTAGRAM_REDIRECT_URI +
                "&scope=user_profile,user_media" +
                "&response_type=code";
    }

    public String getToken(String code) {
        final RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.instagram.com/oauth/access_token?" +
                "client_id=" + INSTAGRAM_ID +
                "client_secret=" + INSTAGRAM_CLIENT_SECRET +
                "grant_type=authorization_code" +
                "redirect_uri=" + INSTAGRAM_REDIRECT_URI +
                "code=" + code;
        final String answer = restTemplate.postForObject(url, null, String.class);
//        JsonObject jsonObject = new Gson().fromJson(answer, JsonObject.class);
//
//        if (!jsonObject.get("error").getAsString().isEmpty()) {
//            throw new RuntimeException("Invalid token");
//        }
//        return jsonObject.get("access_token").getAsString();
        return answer;
    }

    public List<String> getPosts(String token) {
        return new ArrayList<>();
    }
}
