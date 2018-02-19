package com.example.user.trial_android_sql;

/**
 * Created by User on 9/2/2018.
 */

public class User {

    private String username, email, gender;

    public User(String username, String email, String gender) {
        this.username = username;
        this.email = email;
        this.gender = gender;
    }


    //getters
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }
}