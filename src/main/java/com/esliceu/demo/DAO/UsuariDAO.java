package com.esliceu.demo.DAO;

import com.esliceu.demo.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsuariDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public void addUser(User user){
        jdbcTemplate.update("INSERT INTO `users` ( `userName`, `password`, `realName`, `realSurname`)" +
                " VALUES(?,?,?,?)",user.getUsername(),user.getPassword(),user.getRealName(), user.getSurname());
    }
    public List<String> usernameList(){
        String sql = "SELECT userName FROM users";
        List<String> usernames = jdbcTemplate.query(sql, RowMapper<String>);
        return usernames;
    }
}
