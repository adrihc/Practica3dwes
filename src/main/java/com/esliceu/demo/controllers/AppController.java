package com.esliceu.demo.controllers;

import com.esliceu.demo.Model.User;
import com.esliceu.demo.Model.UserForm;
import com.esliceu.demo.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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
    public String PostSignup(UserForm userform, BindingResult bindingResult){
        List<ObjectError> errors = bindingResult.getAllErrors();
        if (bindingResult.hasErrors()){
            String userNameErr="";
            String realNameErr="";
            String realSurnameErr="";
            String passwordErr="";
            for (var e:errors){
                if (e instanceof FieldError){
                    if (((FieldError)e).getField().equals("userName")){
                        userNameErr = "User Name:" + e.getDefaultMessage();
                    }
                    if (((FieldError)e).getField().equals("realName")){
                        realNameErr = "Real Name:" + e.getDefaultMessage();
                    }
                    if (((FieldError)e).getField().equals("realSurname")){
                        realSurnameErr = "Surname:" + e.getDefaultMessage();
                    }
                    if (((FieldError)e).getField().equals("password")){
                        passwordErr = "Password:" + e.getDefaultMessage();
                    }
                }
            }

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
