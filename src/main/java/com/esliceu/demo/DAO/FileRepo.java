package com.esliceu.demo.DAO;

import com.esliceu.demo.Model.Bucket;
import com.esliceu.demo.Model.DataFile;
import com.esliceu.demo.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.crypto.Data;
import java.util.List;

@Repository
public class FileRepo implements FileRepoInterface{
    @Autowired
    JdbcTemplate jdbcTemplate;
    public void addFile(DataFile file){
        jdbcTemplate.update("INSERT INTO `files` ( `file`, `object_id`,`length`,`fileName`,`extension`)" +
                " VALUES(?,?,?,?,?)",file.getBody(),file.getObject_id(),file.getContentLength(),file.getFileName(),file.getExtension());
    }
    public DataFile recoverFile(int objectId){
        List<DataFile> files = jdbcTemplate.query("SELECT * FROM files where `object_id`= ?",fileMapper,objectId);
        return files.get(0);
    }
    public void updateFile(DataFile dataFile){
        String sql = "UPDATE `files` SET `file` = ?, `length` = ? WHERE `files`.`id` = ? ;";
        jdbcTemplate.update(sql, dataFile.getBody(), dataFile.getContentLength(), dataFile.getId());
    }
    private final RowMapper<DataFile> fileMapper=(rs, rn)->{
        DataFile dataFile = new DataFile();
        dataFile.setFileName(rs.getString("fileName"));
        dataFile.setContentLength(rs.getLong("length"));
        dataFile.setBody(rs.getBytes("file"));
        dataFile.setExtension(rs.getString("extension"));
        dataFile.setObject_id(rs.getInt("object_id"));
        dataFile.setId(rs.getInt("id"));
        return dataFile;
    };
}
