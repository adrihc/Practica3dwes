package com.esliceu.demo.Services;

import com.esliceu.demo.DAO.FileRepo;
import com.esliceu.demo.DAO.ObjectsRepo;
import com.esliceu.demo.Model.Bucket;
import com.esliceu.demo.Model.DataFile;
import com.esliceu.demo.Model.Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class ObjectService {
    @Autowired
    ObjectsRepo objectsRepo;
    @Autowired
    FileRepo fileRepo;
    public void addObject(String name, String description, String owner, int bucket_id, int version){
        Object object = new Object();
        object.setVersion(version);
        object.setName(name);
        object.setDescription(description);
        object.setOwner(owner);
        object.setBucket_id(bucket_id);
        object.setLastModified(new Date());
        objectsRepo.addObject(object);
    }
    public List<Object> recoverObjects(Bucket bucket){
        return objectsRepo.recoverObjects(bucket.getId());
    }
    public Object specificObject(String name, int bucket_id){
        List<Object> objects= objectsRepo.recoverObjects(bucket_id);
        for (Object o:objects) {
            if (o.getName().equals(name)){
                return o;
            }
        }
        return null;
    }
    public void deleteObject(String name, int id){
        objectsRepo.delete(name, id);
    }
    public void updateObject(Object object, MultipartFile multipartFile) throws IOException {
        DataFile dataFile = fileRepo.recoverFile(object.getId());
        dataFile.setBody(multipartFile.getBytes());
        dataFile.setContentLength(multipartFile.getSize());
        fileRepo.updateFile(dataFile);
        object.setVersion(object.getVersion()+1);
        objectsRepo.updateObject(object);
    }

}