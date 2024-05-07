//package com.goodReads.library.service.impl;
//
//import com.goodReads.library.Repositry.UserRepository;
//import com.goodReads.library.domain.User;
//import com.goodReads.library.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class UserServiceImpl implements UserService {
//
//    @Autowired
//    UserRepository userRepository;
//    @Override
//    public void addUser(User user) {
//        userRepository.save(user);
//    }
//
//    public void deleteUser(Integer id){
//
//        userRepository.deleteById(id);
//    }
//
//    public boolean userExists(Integer id) {
//        return userRepository.existsById(id);
//    }
//
//    public User updateUser(Integer Id, User updatedUserData) {
//        Optional<User> optionalOriginalUser = userRepository.findById(Id);// retrieve the original book entity from the repository based on the provided Id using
//        // the findById method of the bookRepository. The result is wrapped in an Optional to handle cases where the book may not exist.
////        The Optional<Book> type was introduced in Java 8  to deal with potentially null values in a safer and more concise manner
////Presence of Value:If a value of type Book is present, it is wrapped inside an Optional<Book> instance using the of() method or other factory methods provided by the Optional class.
//// Absence of a Value: If the value is absent (i.e., null), an empty Optional<Book> instance is created using the empty() method.
////                Avoiding NullPointerExceptions:
////        Optional<Book> helps prevent NullPointerExceptions by providing methods to safely access and manipulate the value without directly exposing it.
////        Operations on Optional:
////        Optional<Book> provides various methods to perform operations on the wrapped value, such as get(), isPresent(), orElse(), orElseGet(), orElseThrow(), etc.
//        if (optionalOriginalUser.isPresent()) { // Check if the original book entity exists in the repository
//            // If the original book exists, update it with the provided book entity
//            // This assumes that the book entity passed as an argument contains the updated data
//            User originalUser = optionalOriginalUser.get();
//            originalUser.setEmail(updatedUserData.getEmail());
//            originalUser.setPassword(updatedUserData.getPassword());
//            originalUser.setPhonenumber(updatedUserData.getPhonenumber());
//            originalUser.setUsername(updatedUserData.getUsername());
//
//            return userRepository.save(originalUser);
//        }else {
//            // Handle the case where the book with the provided ID does not exist
//            return null;
//        }
//    }
//
////    @Override
////    public List<User> getAllUsers() {
////        return null;
////    }
////
////    @Override
////    public User getUser(Integer Id) {
////        return null;
////    }
//}
