package com.esliceu.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Other {
    @GetMapping("/error")
    public String ObjectsBuckets(){
        return "error";
    }
    @GetMapping("/")
    public String Start(){
        return "start";
    }
}
