package com.goodReads.library.service;

import com.goodReads.library.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {

    public void addUser(User user) ;// since this is an unchecked exception we don't need to add throws UsernameNotFoundException
//    public List<User> getAllUsers();
//    public User getUser(Integer Id);

    public User updateUser(Integer id, User user);

    public void deleteUser(Integer Id);

    public boolean userExists(Integer id) ;

    }
