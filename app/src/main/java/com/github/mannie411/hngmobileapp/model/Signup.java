package com.github.mannie411.hngmobileapp.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Signup {

    private String Email;
    private String Username;
    private String Password;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public Signup() {
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
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

    public Signup(String email, String username, String password) {
        Email = email;
        Username = username;
        Password = password;
    }
}