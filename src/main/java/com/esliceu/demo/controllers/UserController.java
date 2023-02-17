package com.esliceu.demo.controllers;

import com.esliceu.demo.Model.Bucket;
import com.esliceu.demo.Model.User;
import com.esliceu.demo.Services.BucketService;
import com.esliceu.demo.Services.FileService;
import com.esliceu.demo.Services.ObjectService;
import com.esliceu.demo.Services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    BucketService bucketService;
    @Autowired
    ObjectService objectService;
    @Autowired
    FileService fileService;

    @GetMapping("/login")
    public String Login(){

        return "login";
    }
    @PostMapping("/login")
    public String PostLogin(String userName, String password, HttpServletRequest sr, HttpSession session, HttpServletResponse resp) throws IOException {
        System.out.println(userName);
        if (userService.login(userName,password)){
            User user = userService.getUser(userName);
            session.setAttribute("user", user);
            resp.sendRedirect("objects");
        } else {
            sr.setAttribute("loginError","La contraseña o el nombre de usuario son erroneos");
            return "login";
        }
        return "login";
    }

    @GetMapping("/signup")
    public String Signup(){
        return "signup";
    }
    @PostMapping("/signup")
    public String PostSignup(String userName, String password, String realName, String surname, HttpServletRequest sr, HttpServletResponse resp) throws IOException {
        if (userName.isEmpty()||password.isEmpty()||realName.isEmpty()||surname.isEmpty()) {
            sr.setAttribute("errormessage","Todos los campos son obligatorios");
            return "signup";
        }
        if (userService.tryExistence(userName)){
            sr.setAttribute("errormessageName","Ese nombre de usuario ya existe");
        } else if (password.length()>18) {
            sr.setAttribute("errormessagePassword","La contraseña debe contener como máximo 18 caracteres");
        } else if (userService.tryExistence(userName)||password.length()<=18){
            userService.addUser(userName,password,realName,surname);
            resp.sendRedirect("login");
        }
        return "signup";
    }
    @GetMapping("/settings")
    public String Settings(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        model.addAttribute("realName", user.getRealName());
        model.addAttribute("surname", user.getSurname());
        return "settings";
    }
    @PostMapping("/settings")
    public String postSettings(HttpSession session,String password, String realName, String surname, Model model){
        User user = (User) session.getAttribute("user");
        user.setRealName(realName);
        if (password.isEmpty()){
            password = user.getPassword();
        }
        user.setPassword(password);
        user.setSurname(surname);
        userService.update(user);
        session.setAttribute("user", user);
        model.addAttribute("realName", user.getRealName());
        model.addAttribute("surname", user.getSurname());
        return "settings";
    }
    @GetMapping("/objects")
    public String Objects(HttpSession session, Model model, HttpServletRequest sr){
        User user = (User) session.getAttribute("user");
        sr.setAttribute("user", user.getUsername());
        List<Bucket> buckets = bucketService.recoverBuckets(user.getUsername());
        model.addAttribute("buckets", buckets);
        return "objects";
    }

    @PostMapping("/objects")
    public String postObjects(HttpSession session, HttpServletRequest sr,String bucketName, Model model){
        User user = (User) session.getAttribute("user");
        List<Bucket> buckets = bucketService.recoverBuckets(user.getUsername());
        for (Bucket b:buckets) {
            if (b.getName().equals(bucketName)){
                String bucketError = "Ese nombre ya está en uso";
                model.addAttribute("bucketError", bucketError);
                model.addAttribute("buckets", buckets);
                return "objects";
            }
        }
        if (bucketName.isEmpty()){
            String bucketError = "Ese nombre no es valido";
            model.addAttribute("bucketError", bucketError);
            model.addAttribute("buckets", buckets);
            return "objects";
        } else {
            bucketService.addBucket(user, bucketName);
            buckets = bucketService.recoverBuckets(user.getUsername());
            sr.setAttribute("user", user.getUsername());
            model.addAttribute("buckets", buckets);
            return "objects";
        }
    }

    @PostMapping("/deleteuser")
    public void deleteUser(HttpSession session, HttpServletResponse resp) throws IOException {
        User user = (User) session.getAttribute("user");
        userService.deleteUser(user);
        resp.sendRedirect("/login");
    }

}