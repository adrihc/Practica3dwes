package com.esliceu.demo.DAO;

import com.esliceu.demo.Model.Bucket;
import com.esliceu.demo.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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
        String sql = "SELECT * FROM buckets WHERE owner = ?";
        List<Bucket> buckets = jdbcTemplate.query(sql, bucketMapper,username);
        return buckets;
    }
    public void delete(String bucket, String owner){
        String sql = "DELETE FROM buckets WHERE owner = ? AND `name` = ? ";
        jdbcTemplate.update(sql,owner,bucket);
    }
    public Bucket getSpecificBucket(String name, String owner){
        List<Bucket> bucket = jdbcTemplate.query("SELECT * FROM buckets WHERE name = ? AND owner = ?", bucketMapper,
        name, owner);
        return bucket.get(0);
    }
    private final RowMapper<Bucket> bucketMapper=(rs, rn)->{
        Bucket bucket = new Bucket();
        bucket.setId(rs.getInt("id"));
        bucket.setName(rs.getString("name"));
        bucket.setUri(rs.getString("uri"));
        bucket.setOwner(rs.getString("owner"));
        return bucket;
    };
}
