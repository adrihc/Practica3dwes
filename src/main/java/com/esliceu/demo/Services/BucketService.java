package com.esliceu.demo.Services;

import com.esliceu.demo.DAO.BucketRepo;
import com.esliceu.demo.Model.Bucket;
import com.esliceu.demo.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BucketService {
    @Autowired
    BucketRepo bucketRepo;

    public void addBucket(User user, String bucketName){
        Bucket bucket = new Bucket();
        bucket.setOwner(user.getUsername());
        bucket.setName(bucketName);
        bucket.setUri("1234");
        bucketRepo.addBucket(bucket);
    }
}
