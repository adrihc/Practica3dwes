package com.esliceu.demo.DAO;

import com.esliceu.demo.Model.User;

import java.util.List;

public interface UserRepoInterface {
    void addUser(User user);
    List<User> userList();
    User getUser(String username);
    void update(User user);
    void deleteUser(User user);
}
