package com.example.services.model;

import com.fasterxml.jackson.annotation.JsonProperty;


import javax.persistence.*;

import java.io.Serializable;

import java.util.Date;


@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long id;
    private String fullName;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String email;
    private Date lastLoginDate;
    private Date logInDateDisplay;
    private String role;
    private String[] authorities;
    private boolean isActive;
    private boolean isNotLocked;

    public User() {}

    public User(Long id,String fullName, String password, String email,
                Date lastLoginDate, Date logInDateDisplay, Date joinDate, String role, String[] authorities,
                boolean isActive, boolean isNotLocked) {
        this.id = id;
        this.fullName = fullName;
        this.password = password;
        this.email = email;
        this.lastLoginDate = lastLoginDate;
        this.logInDateDisplay = logInDateDisplay;
        this.role = role;
        this.authorities = authorities;
        this.isActive = isActive;
        this.isNotLocked = isNotLocked;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Date getLogInDateDisplay() {
        return logInDateDisplay;
    }

    public void setLogInDateDisplay(Date logInDateDisplay) {
        this.logInDateDisplay = logInDateDisplay;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String[] getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String ...authorities) {
        this.authorities = authorities;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isNotLocked() {
        return isNotLocked;
    }

    public void setNotLocked(boolean isNotLocked) {
        this.isNotLocked = isNotLocked;
    }

}

