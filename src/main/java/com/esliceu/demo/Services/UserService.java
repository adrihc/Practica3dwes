package com.esliceu.demo.Services;

import com.esliceu.demo.DAO.UserRepo;
import com.esliceu.demo.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepo userDAO;

    public void addUser(String userName,String password,String realName,String surname){
        User user = new User();
        user.setUsername(userName);
        user.setPassword(password);
        user.setRealName(realName);
        user.setSurname(surname);
        userDAO.addUser(user);
    }

    public boolean tryExistence(String username){
        for (User userList:userDAO.userList()) {
            if (userList.getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }

    public boolean login(String userName, String password){
        boolean isLogin = false;
        for (User user: userDAO.userList()) {
            if (user.getUsername().equals(userName) && user.getPassword().equals(password)){
                isLogin = true;
            }
        }
        return isLogin;
    }

    public User getUser(String userName) {
        return userDAO.getUser(userName);
    }
    public void update(User user){
        userDAO.update(user);
    }

    public void deleteUser(User user) {
        userDAO.deleteUser(user);
    }
}
