package com.esliceu.demo.Services;

import com.esliceu.demo.DAO.ObjectsRepo;
import com.esliceu.demo.Model.Bucket;
import com.esliceu.demo.Model.Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObjectService {
    @Autowired
    ObjectsRepo objectsRepo;
    public void addObject(String name, String description, String owner, int bucket_id){
        Object object = new Object();
        object.setName(name);
        object.setDescription(description);
        object.setOwner(owner);
        object.setBucket_id(bucket_id);
        objectsRepo.addObject(object);
    }
    public List<Object> recoverObjects(Bucket bucket){
        return objectsRepo.recoverObjects(bucket.getId());
    }
}
