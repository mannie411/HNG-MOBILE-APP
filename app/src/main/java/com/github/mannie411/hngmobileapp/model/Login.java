package com.github.mannie411.hngmobileapp.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Login {


    private String Username;
    private String Password;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public Login() {
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        this.Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public Login(String username, String password) {

        Username = username;
        Password = password;
    }
}