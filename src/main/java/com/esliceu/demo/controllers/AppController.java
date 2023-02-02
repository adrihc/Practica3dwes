package com.esliceu.demo.controllers;

import com.esliceu.demo.Services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {
    @Autowired
    UserService userService;
    private UserForm userform;
    private BindingResult bindingResult;

    @GetMapping("/login")
    public String Login(){

        return "login";
    }
    @PostMapping("/login")
    public String PostLogin(){


        return "login";
    }
    @GetMapping("/signup")
    public String Signup(){
        return "signup";
    }
    @PostMapping("/signup")
    public String PostSignup(String user, String password, String realName, String surname, HttpServletRequest sr){
        System.out.println(user + password +realName + surname);
        if (user.isEmpty()||password.isEmpty()||realName.isEmpty()||surname.isEmpty()) {
            sr.setAttribute("errormessage","Todos los campos son obligatorios");
        }
        String userNameErr="Ese nombre de usuario ya existe";
        String passwordErr="La contraseña debe contener como máximo 18 caracteres";

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
