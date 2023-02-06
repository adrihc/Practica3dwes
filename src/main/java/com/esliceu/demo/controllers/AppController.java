package com.esliceu.demo.controllers;

import com.esliceu.demo.DAO.UsuariDAO;
import com.esliceu.demo.Model.User;
import com.esliceu.demo.Services.UserService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.awt.*;

@Controller
public class AppController {
    @Autowired
    UserService userService;
    @Autowired
    UsuariDAO usuariDAO;
    private BindingResult bindingResult;

    @GetMapping("/login")
    public String Login(){
        return "login";
    }
    @PostMapping("/login")
    public String PostLogin(String userName, String password, HttpServletRequest sr){

        if (userService.login(userName,password)){
            //Meter al usuario en la sesión y hacer un redirect hacia la nueva página
        } else {
            sr.setAttribute("loginError","La contraseña o el nombre de usuario son erroneos");
            return "login";
        }

        return "login";
    }
    @GetMapping("/signup")
    public String Signup(){
        usuariDAO.usernameList();
        return "signup";
    }
    @PostMapping("/signup")
    public String PostSignup(String userName, String password, String realName, String surname, HttpServletRequest sr, Model model){
        if (userName.isEmpty()||password.isEmpty()||realName.isEmpty()||surname.isEmpty()) {
            sr.setAttribute("errormessage","Todos los campos son obligatorios");
            return "signup";
        }
        if (userService.tryExistence(userName)){
            sr.setAttribute("errormessageName","Ese nombre de usuario ya existe");
        } else if (password.length()>18) {
            sr.setAttribute("errormessagePassword","La contraseña debe contener como máximo 18 caracteres");
        } else if (!userService.tryExistence(userName)||password.length()<=18){
            User user = new User(userName,password,realName,surname);
            userService.addUser(user);

            //Hacer un redirect a /login
            return "login";
        }

        return "signup";
    }
    @GetMapping("/settings")
    public String Settings(){
        return "start";
    }
    @GetMapping("/objects")
    public String Objects(){

        return "";
    }
    @GetMapping("/objects/{bucket}")
    public String ObjectsBuckets(){

        return "";
    }
    @GetMapping("/objects/{bucket}/{prefix}")
    public String ObjectsPrefix(){

        return "";
    }
    @GetMapping("/objects/{bucket}/{object}")
    public String ObjectsObject(){

        return "";
    }

}
