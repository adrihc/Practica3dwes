package com.esliceu.demo.DAO;

import com.esliceu.demo.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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

    public void addUser(User user){
        jdbcTemplate.update("INSERT INTO `users` ( `userName`, `password`, `realName`, `realSurname`)" +
                " VALUES(?,?,?,?)",user.getUsername(),user.getPassword(),user.getRealName(), user.getSurname());
    }
    public List<User> userList(){
        String sql = "SELECT * FROM users";
        List<User> usernames = jdbcTemplate.query(sql, userMapper);
        return usernames;
    }

    public User getUser(String username){
        String sql = "SELECT * FROM users WHERE userName = ?";
        List<User> user = jdbcTemplate.query(sql, userMapper,username);
        return user.get(0);
    }
    private final RowMapper<User> userMapper=(rs, rn)->{
        User user = new User();
        user.setUsername(rs.getString("userName"));
        user.setPassword(rs.getString("password"));
        user.setRealName(rs.getString("realName"));
        user.setSurname(rs.getString("realSurname"));
        return user;
    };
}
