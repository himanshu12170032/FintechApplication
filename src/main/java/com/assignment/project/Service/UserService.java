package com.assignment.project.Service;

import com.assignment.project.Entity.User;
import com.assignment.project.Exception.UserNotFoundException;
import com.assignment.project.Request.UpdateUserRequest;

public interface UserService {
    public User findUserByProfile(String jwt) throws UserNotFoundException;
    public User findUserById(Long id) throws UserNotFoundException;
    public User updateUser(Long userId, UpdateUserRequest req) throws UserNotFoundException;

}
