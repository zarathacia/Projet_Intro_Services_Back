package com.example.services.service.implementation;

import com.example.services.enumeartion.Role;
import com.example.services.exception.domain.EmailExistException;
import com.example.services.exception.domain.EmailNotFoundException;
import com.example.services.exception.domain.UserNotFoundException;
import com.example.services.exception.domain.UsernameExistException;
import com.example.services.model.User;
import com.example.services.model.UserPrincipal;
import com.example.services.repository.UserRepo;
import com.example.services.service.LoginAttemptService;
import com.example.services.service.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static com.example.services.constant.UserImplConstant.*;
import static com.example.services.enumeartion.Role.ROLE_ADMIN;
import static org.apache.commons.lang3.StringUtils.EMPTY;


@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final UserRepo userRepo;
    private final BCryptPasswordEncoder passwordEncoder;
    private final LoginAttemptService loginAttemptService;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, BCryptPasswordEncoder passwordEncoder, LoginAttemptService loginAttemptService) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.loginAttemptService = loginAttemptService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findUserByEmail(email);
        if (user == null) {
            LOGGER.error(NO_USER_BY_EMAIL + email);
            throw new UsernameNotFoundException(NO_USER_BY_EMAIL + email);
        } else {
            validateLoginAttempt(user);
            user.setLogInDateDisplay(user.getLastLoginDate());
            user.setLastLoginDate(new Date());
            userRepo.save(user);
            UserPrincipal userPrincipal = new UserPrincipal(user);
            LOGGER.info(USER_FROM_USER_DETAILS_SERVICE + email);
            return userPrincipal;
        }

    }

    @Override
    public User register(String fullName, String email, String password)
            throws EmailExistException, UsernameExistException, UserNotFoundException {
        validateNewFullNameAndEmail(EMPTY, fullName, email);
        User user = new User();
        String encodedPassword = encodePassword(password);
        user.setFullName(fullName);
        user.setEmail(email);
        user.setPassword(encodedPassword);
        user.setActive(true);
        user.setNotLocked(true);
        user.setAuthorities(ROLE_ADMIN.getAuthorities());
        user.setRole(ROLE_ADMIN.name());
        userRepo.save(user);
        LOGGER.info(password);
        return user;
    }

    @Override
    public User addNewUser(String fullName, String email, String role,
                           boolean isNonlocked, boolean isActive, MultipartFile profileImage)
            throws EmailExistException, UsernameExistException, UserNotFoundException {
        validateNewFullNameAndEmail(EMPTY, fullName, email);
        User user = new User();
        String password = generatePassword();
        String encodedPassword = encodePassword(password);
        user.setFullName(fullName);
        user.setEmail(email);
        user.setPassword(encodedPassword);
        user.setActive(isActive);
        user.setNotLocked(isNonlocked);
        user.setRole(getRoleEnumName(role).name());
        user.setAuthorities(getRoleEnumName(role).getAuthorities());
        userRepo.save(user);
        LOGGER.info(password);
        return user;
    }

    @Override
    public User updateUser(String currentFullname, String newFullname,
                           String newEmail, String role, boolean isNonlocked, boolean isActive)
            throws EmailExistException, UsernameExistException, UserNotFoundException {
        User currentUser = validateNewFullNameAndEmail(currentFullname, newFullname, newEmail);
        currentUser.setFullName(newFullname);
        currentUser.setEmail(newEmail);
        currentUser.setActive(isActive);
        currentUser.setNotLocked(isNonlocked);
        currentUser.setRole(getRoleEnumName(role).name());
        currentUser.setAuthorities(getRoleEnumName(role).getAuthorities());
        userRepo.save(currentUser);
        return currentUser;
    }

    @Override
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public User findUserByFullName(String fullName) {
        return userRepo.findUserByFullName(fullName);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepo.findUserByEmail(email);
    }

    @Override
    public void deleteUser(String fullName) throws IOException {
        User user = userRepo.findUserByFullName(fullName);
        userRepo.deleteById(user.getId());
    }

    @Override
    public void resetPassword(String email) throws EmailNotFoundException {
        User user = userRepo.findUserByEmail(email);
        if (user == null) {
            throw new EmailNotFoundException(NO_USER_BY_EMAIL + email);
        }
        String password = generatePassword();
        user.setPassword(encodePassword(password));
        LOGGER.info(password);
        userRepo.save(user);
    }

    private void validateLoginAttempt(User user) {
        if (user.isNotLocked()) {
            if (loginAttemptService.hasExceededMaxAttempts(user.getFullName())) {
                user.setNotLocked(false);
            } else {
                user.setNotLocked(true);
            }
        } else {
            loginAttemptService.evictUserFromLoginAttemptCache(user.getFullName());
        }
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    private String generatePassword() {
        return RandomStringUtils.randomAlphanumeric(10);
    }

    private Role getRoleEnumName(String role) {
        return Role.valueOf(role.toUpperCase());
    }

    private User validateNewFullNameAndEmail(String currentFullName, String newFullName, String newEmail)
            throws UserNotFoundException, UsernameExistException, EmailExistException {
        User userByNewUsername = findUserByFullName(newFullName);
        User userByNewEmail = findUserByEmail(newEmail);
        if (StringUtils.isNotBlank(currentFullName)) {
            User currentUser = findUserByFullName(currentFullName);
            if (currentUser == null) {
                throw new UserNotFoundException(USER_NOT_FOUND + currentFullName);
            }
            if (userByNewUsername != null && !currentUser.getId().equals(userByNewUsername.getId())) {
                throw new UsernameExistException(FULLNAME_EXISTS);
            }
            if (userByNewEmail != null && !currentUser.getId().equals(userByNewEmail.getId())) {
                throw new EmailExistException(EMAIL_EXISTS);
            }
            return currentUser;
        } else {
            if (userByNewUsername != null) {
                throw new UsernameExistException(FULLNAME_EXISTS);
            }
            if (userByNewEmail != null) {
                throw new EmailExistException(EMAIL_EXISTS);
            }
            return null;
        }
    }
}
