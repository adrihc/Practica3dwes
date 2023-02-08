package com.esliceu.demo.DAO;

import com.esliceu.demo.Model.Bucket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BucketRepo {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public void addBucket(Bucket bucket){
        jdbcTemplate.update("INSERT INTO `buckets` ( `name`, `uri`, `owner`)" +
                " VALUES(?,?,?)",bucket.getName(),bucket.getUri(), bucket.getOwner());
    }
    public List<Bucket> recoverBucket(String username){
        return null;
    }
}
