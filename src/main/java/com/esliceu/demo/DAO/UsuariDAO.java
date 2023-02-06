package com.esliceu.demo.DAO;

import com.esliceu.demo.Model.Login;
import com.esliceu.demo.Model.LoginRowMapper;
import com.esliceu.demo.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
        List<String> usernames = jdbcTemplate.queryForList(sql, String.class);
        return usernames;
    }

    public List<Login> login(){
        String sql= " SELECT (`userName`, `password`) FROM users";
        //List<Login> loginList = jdbcTemplate.queryForList(sql, new LoginRowMapper());
        List<Login> loginList = new ArrayList<>();
        return loginList;
    }
}
