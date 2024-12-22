package com.assignment.project.Controller;

import com.assignment.project.Entity.User;
import com.assignment.project.Exception.UserNotFoundException;
import com.assignment.project.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfile(@RequestHeader("Authorization") String jwt) throws UserNotFoundException {
       System.out.println("Profile search :" + jwt);
        User user = userService.findUserByProfile(jwt);
        if(user == null){
            throw new UserNotFoundException("user not found");
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
