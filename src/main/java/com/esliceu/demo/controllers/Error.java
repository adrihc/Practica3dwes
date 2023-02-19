package com.esliceu.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Error {
    @GetMapping("/error")
    public String ObjectsBuckets(){
        return "error";
    }
}
