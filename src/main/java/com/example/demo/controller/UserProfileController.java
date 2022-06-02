package com.example.demo.controller;

import com.example.demo.mapper.UserProfileMapper;
import com.example.demo.model.UserProfile;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserProfileController {

    private UserProfileMapper mapper;
    public UserProfileController(UserProfileMapper mapper){
        this.mapper = mapper;
    }


    @GetMapping("/users/{id}")
    public UserProfile getUserprofile(@PathVariable("id") String id){
        return mapper.getUserprofile(id);
    }

    @GetMapping("/users")
    public List<UserProfile> getUserProfileList(){
        return mapper.getUserProfileList();
    }

    @PostMapping(value = "/users")
    public void createUserProfile(@RequestBody UserProfile profile){
        mapper.insertUserProfile(profile.getId(), profile.getName(), profile.getPhone(), profile.getAddress());
    }

    @PutMapping("/users/{id}")
    public void updateUserProfile(@PathVariable("id") String id, @RequestParam("name") String name, @RequestParam("phone") String phone, @RequestParam("address") String address){
        mapper.updateUserProfile(id, name, phone, address);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUserProfile(@PathVariable("id") String id){
        mapper.deleteUserProfile(id);
    }
}
