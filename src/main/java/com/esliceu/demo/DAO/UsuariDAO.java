package com.esliceu.demo.DAO;

import com.esliceu.demo.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsuariDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void addUser(User user){
        jdbcTemplate.update("INSERT INTO `users` ( `userName`, `password`, `realName`, `realSurname`)" +
                " VALUES(?,?,?,?)",user.getUsername(),user.getPassword(),user.getRealName(), user.getSurname());
    }
    public List<User> userList(){
        String sql = "SELECT * FROM users";
        List<User> usernames = jdbcTemplate.queryForList(sql, User.class);
        return usernames;
    }
    public User getuser(String userName){
        String sql = "SELECT * FROM users WHERE `userName` = ?";
        return jdbcTemplate.queryForObject(sql,User.class, userName);
    }
}
