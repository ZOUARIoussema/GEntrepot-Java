/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.models;

import java.util.Objects;
import javafx.scene.control.CheckBox;

/**
 *
 * @author oussema
 */
public class User {

    private int id;
    private String username;
    private String email;
    private String usernamCanonical;
    private String emailCanonical;
    private String password;
    private String role;
    
    
    
    private CheckBox checkBox = new CheckBox();

    public User(int id, String username, String email, String usernamCanonical, String emailCanonical, String password, String role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.usernamCanonical = usernamCanonical;
        this.emailCanonical = emailCanonical;
        this.password = password;
        this.role = role;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    
    
    
    

    public User(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsernamCanonical() {
        return usernamCanonical;
    }

    public void setUsernamCanonical(String usernamCanonical) {
        this.usernamCanonical = usernamCanonical;
    }

    public String getEmailCanonical() {
        return emailCanonical;
    }

    public void setEmailCanonical(String emailCanonical) {
        this.emailCanonical = emailCanonical;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", email=" + email + ", usernamCanonical=" + usernamCanonical + ", emailCanonical=" + emailCanonical + ", password=" + password + ", role=" + role + '}';
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        return true;
    }
    
    
    
    
    

}
