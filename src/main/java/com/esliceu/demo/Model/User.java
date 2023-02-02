package com.esliceu.demo.Model;

public class User {
    String username;
    String password;
    String realName;
    String surname;

    public User(String username, String password, String realName, String surname) {
        this.username = username;
        this.password = password;
        this.realName = realName;
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
