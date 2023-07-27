package com.alibou.security.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public User getUserByEmail(String email) {
        String sql = "SELECT id, firstname, lastname, email " +
                "FROM user WHERE email = :email";

        Map<String, Object> map = new HashMap<>();
        map.put("email", email);

        List<User> productList = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());

        if (productList.isEmpty()) {
            return null;
        }

        return productList.get(0);
    }

}
