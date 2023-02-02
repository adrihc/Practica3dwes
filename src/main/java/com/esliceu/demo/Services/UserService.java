package com.esliceu.demo.Services;

import com.esliceu.demo.DAO.UsuariDAO;
import com.esliceu.demo.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UsuariDAO userDAO;

    public void addUser(User user){
        userDAO.addUser(user);
    }

}
