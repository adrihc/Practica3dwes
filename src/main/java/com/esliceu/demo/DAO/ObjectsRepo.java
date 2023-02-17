package com.esliceu.demo.DAO;

import com.esliceu.demo.Model.Object;
import com.esliceu.demo.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ObjectsRepo {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public void addObject(Object object){
        jdbcTemplate.update("INSERT INTO `objects` ( `name`, `description`, `owner`, `bucket_id`,`version`)" +
                " VALUES(?,?,?,?,?)",object.getName(),object.getDescription(),object.getOwner(),object.getBucket_id(),object.getVersion());
    }
    public List<Object> recoverObjects(int id){
        List<Object> objects = jdbcTemplate.query("SELECT * FROM objects where `bucket_id`=?",objectMapper,id);
        return objects;
    }
    public void delete(String name, int id){
        String sql = "DELETE FROM objects WHERE `name` = ? AND `id` = ? ";
        jdbcTemplate.update(sql,name,id);
    }
    public void updateObject(Object object) {
        String sql = "UPDATE `objects` SET `version` = ? WHERE `objects`.`id` = ?;";
        jdbcTemplate.update(sql,object.getVersion(),object.getId());
    }
    private final RowMapper<Object> objectMapper=(rs, rn)->{
        Object object = new Object();
        object.setId(rs.getInt("id"));
        object.setName(rs.getString("name"));
        object.setDescription(rs.getString("description"));
        object.setOwner(rs.getString("owner"));
        object.setVersion(rs.getInt("version"));
        object.setBucket_id(rs.getInt("bucket_id"));
        object.setLastModified(rs.getDate("lastModified"));
        object.setLastModifiedTime(rs.getTime("lastModified"));
        return object;
    };


}
