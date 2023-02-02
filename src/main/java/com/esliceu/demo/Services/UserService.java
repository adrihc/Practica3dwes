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
    public User getUser(String username){
        User user = new User("Adri","1234");
        return user;
    }
    public void newUser(String username, String password){

    }
    public boolean tryExistence(String username){
        //Crear una clase que llame a usuarioDAO.usernameList y devuelva un booleano que diga si existe o no
        //el usuario en la base de datos para luego hacer una comprobación en el controlador
        return true;
    }
}
