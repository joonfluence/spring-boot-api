package com.example.demo.controller;

import com.example.demo.model.UserProfile;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserProfileController {

    private Map<String, UserProfile> userMap;

    @PostConstruct
    public void init(){
        userMap = new HashMap<String, UserProfile>();
        userMap.put("1", new UserProfile("1", "홍길동", "111-1111", "서울시 강남구 대치1동"));
        userMap.put("2", new UserProfile("2", "홍길동", "111-1112", "서울시 강남구 대치2동"));
        userMap.put("3", new UserProfile("3", "홍길동", "111-1113", "서울시 강남구 대치3동"));
    }

    @GetMapping("/users/{id}")
    public UserProfile getUserprofile(@PathVariable("id") String id){
        return userMap.get(id);
    }

    @GetMapping("/users")
    public List<UserProfile> getUserProfileList(){
        return new ArrayList<UserProfile>(userMap.values());
    }

    @PostMapping(value = "/users")
    public UserProfile createUserProfile(@RequestBody UserProfile profile){
        UserProfile userProfile = new UserProfile(profile.getId(), profile.getName(), profile.getPhone(), profile.getAddress());
        userMap.put(profile.getId(), userProfile);
        return userMap.get(profile.getId());
    }

    @PutMapping("/users/{id}")
    public UserProfile updateUserProfile(@PathVariable("id") String id, @RequestParam("name") String name, @RequestParam("phone") String phone, @RequestParam("address") String address){
        UserProfile userProfile = userMap.get(id);
        System.out.println("userProfile = " + userProfile);
        userProfile.setName(name);
        userProfile.setPhone(phone);
        userProfile.setAddress(address);
        return userMap.get(id);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUserProfile(@PathVariable("id") String id){
        userMap.remove(id);
    }
}
