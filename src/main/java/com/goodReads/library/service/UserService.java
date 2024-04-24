package com.goodReads.library.service;

import com.goodReads.library.domain.User;

public interface UserService {

    public void addUser(User user);
//    public List<User> getAllUsers();
//    public User getUser(Integer Id);

    public User updateUser(Integer id, User user);

    public void deleteUser(Integer Id);

    public boolean userExists(Integer id) ;

    }
