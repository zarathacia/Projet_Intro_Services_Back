package com.example.services.service;

import com.example.services.exception.domain.EmailExistException;
import com.example.services.exception.domain.EmailNotFoundException;
import com.example.services.exception.domain.UserNotFoundException;
import com.example.services.exception.domain.UsernameExistException;
import com.example.services.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {

    User register(String fullName, String email, String password) throws EmailExistException, UsernameExistException, UserNotFoundException;

    User addNewUser(String fullName, String email, String role, boolean isNonlocked, boolean isActive, MultipartFile profileImage) throws EmailExistException, UsernameExistException, UserNotFoundException, IOException;

    User updateUser(String currentFullName, String newFullName, String newEmail, String role, boolean isNonlocked, boolean isActive) throws EmailExistException, UsernameExistException, UserNotFoundException, IOException;

    List<User> getUsers();

    User findUserByFullName(String username);

    User findUserByEmail(String email);

    void deleteUser(String username) throws IOException;

    void resetPassword(String email) throws EmailNotFoundException;
}
