package com.esliceu.demo.DAO;

import com.esliceu.demo.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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
    public List<User> userList(){
        String sql = "SELECT * FROM users";
        List<User> usernames = jdbcTemplate.queryForList(sql, User.class);
        return usernames;
    }

}
