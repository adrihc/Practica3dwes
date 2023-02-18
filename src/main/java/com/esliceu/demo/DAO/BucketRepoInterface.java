package com.esliceu.demo.DAO;

import com.esliceu.demo.Model.Bucket;

import java.util.List;

public interface BucketRepoInterface {
    void addBucket(Bucket bucket);
    List<Bucket> recoverBucket(String username);
    void delete(String bucket, String owner);
    Bucket getSpecificBucket(String name, String owner);
}
