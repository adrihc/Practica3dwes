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
import java.io.OutputStream;

@Controller
public class ObjectController {
    @Autowired
    UserService userService;
    @Autowired
    BucketService bucketService;
    @Autowired
    ObjectService objectService;
    @Autowired
    FileService fileService;

    @GetMapping("/objects/{bucket}/{object}")
    public String Object(@PathVariable("bucket") String bucket, @PathVariable("object") String object, HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        Bucket myBucket = bucketService.recoverSpecificBucket(bucket,user.getUsername());
        Object myObject = objectService.specificObject(object,myBucket.getId());
        DataFile dataFile = fileService.recoverFile(myObject.getId());
        model.addAttribute("length",dataFile.getContentLength());
        model.addAttribute("version",myObject.getVersion());
        model.addAttribute("objectLastModified",myObject.getLastModified());
        model.addAttribute("objectLastModifiedTime",myObject.getLastModifiedTime());
        model.addAttribute("objectDesc", myObject.getDescription());
        model.addAttribute("objid",myObject.getId());
        model.addAttribute("fid", dataFile.getId());
        return "object";
    }

    @PostMapping("/objects/{bucket}/{object}")
    public void postObject(@PathVariable("bucket") String bucket, @PathVariable("object") String object, HttpSession session, Model model, MultipartFile file, HttpServletResponse resp) throws IOException {
        User user = (User) session.getAttribute("user");
        Bucket myBucket = bucketService.recoverSpecificBucket(bucket,user.getUsername());
        Object myObject = objectService.specificObject(object,myBucket.getId());
        objectService.updateObject(myObject, file);
        resp.sendRedirect("/objects");
    }

    @PostMapping("/deleteobj/{bucket}/{object}")
    public void deleteObject(@PathVariable("bucket") String bucket, @PathVariable("object") String object, HttpSession session, HttpServletResponse resp) throws IOException {
        User user = (User) session.getAttribute("user");
        Bucket myBucket = bucketService.recoverSpecificBucket(bucket, user.getUsername());
        Object myObject = objectService.specificObject(object, myBucket.getId());
        objectService.deleteObject(myObject.getName(), myObject.getId());
        resp.sendRedirect("/objects");
    }

    @GetMapping("/download/{objid}/{fid}")
    public void download(@PathVariable("objid") int objectId, @PathVariable("fid") int fileId, HttpServletResponse resp) throws IOException {
        DataFile dataFile = fileService.recoverFile(objectId);
        resp.setContentType("application/force-download");
        resp.addHeader("Content-disposition", "attachment;fileName=" + dataFile.getFileName());
        OutputStream outputStream = resp.getOutputStream();
        try{
            outputStream.write(dataFile.getBody(),0,(int)dataFile.getContentLength());
        } catch (Exception e){
            throw e;
        }

    }
}
