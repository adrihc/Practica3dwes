package com.esliceu.demo.controllers;

import com.esliceu.demo.Model.Bucket;
import com.esliceu.demo.Model.DataFile;
import com.esliceu.demo.Model.Object;
import com.esliceu.demo.Model.User;
import com.esliceu.demo.Services.BucketService;
import com.esliceu.demo.Services.FileService;
import com.esliceu.demo.Services.ObjectService;
import com.esliceu.demo.Services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class BucketController {
    @Autowired
    UserService userService;
    @Autowired
    BucketService bucketService;
    @Autowired
    ObjectService objectService;
    @Autowired
    FileService fileService;

    @GetMapping("/objects/{bucket}")
    public String ObjectsBuckets(@PathVariable("bucket") String bucketName, HttpSession session, Model model){
        User user= (User) session.getAttribute("user");
        Bucket bucket = bucketService.recoverSpecificBucket(bucketName,user.getUsername());
        List<Object> objects = objectService.recoverObjects(bucket);
        model.addAttribute("objects", objects);
        return "bucket";
    }
    @PostMapping("/objects/{bucket}")
    public String PostObjectsBuckets(@PathVariable("bucket") String bucketName, HttpSession session, String name, String description, Model model, MultipartFile file) throws IOException {
        User user= (User) session.getAttribute("user");
        Bucket bucket = bucketService.recoverSpecificBucket(bucketName,user.getUsername());
        List<Object> objects = objectService.recoverObjects(bucket);
        for (Object o: objects) {
            if (o.getName().equals(name)){
                String objectError = "Ese nombre ya existe";
                model.addAttribute("objectError", objectError);
                model.addAttribute("objects", objects);
                return "bucket";
            }
        }
        if (name.isEmpty()){
            model.addAttribute("objects", objects);
            String objectError = "Elija un nombre";
            model.addAttribute("objectError", objectError);
            model.addAttribute("objects", objects);
            return "bucket";
        } else if (file.isEmpty()) {
            model.addAttribute("objects", objects);
            String objectError = "Elija un archivo";
            model.addAttribute("objectError", objectError);
            model.addAttribute("objects", objects);
            return "bucket";
        } else if (description.isEmpty()||description.length()>300) {
            model.addAttribute("objects", objects);
            String objectError = "Elija una descripción válida";
            model.addAttribute("objectError", objectError);
            model.addAttribute("objects", objects);
            return "bucket";
        } else{
            objectService.addObject(name,description,user.getUsername(),bucket.getId(),1);
            objects = objectService.recoverObjects(bucket);
            Object fileObject = objectService.specificObject(name,bucket.getId());
            DataFile dataFile = fileService.createDataFile(bucket, file,fileObject);
            fileService.addFile(dataFile);
            model.addAttribute("objects", objects);
            return "bucket";
        }
    }

    @PostMapping("/deletebucket/{bucket}")
    public void deleteBucket(@PathVariable("bucket")String bucket, HttpSession session, HttpServletResponse resp) throws IOException {
        User user = (User) session.getAttribute("user");
        bucketService.delete(bucket,user);
        resp.sendRedirect("/objects");
    }
}