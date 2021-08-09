package com.example.demo;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

@Repository
public class DemoDAO {
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public DemoDAO(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public String getNameForUserId(String userID) {
        String sql = "SELECT first_name, last_name from EXAMPLE_TABLE WHERE user_id = :userID";

        SqlParameterSource namedParameters = new MapSqlParameterSource("userID", userID);

        try {
            return this.jdbcTemplate.queryForObject(sql, namedParameters, (resultSet, rowNum) -> {
                StringBuilder str = new StringBuilder();
                str.append(resultSet.getString("first_name"));
                String lastName = resultSet.getString("last_name");
                if (lastName != null) {
                    str.append(" ");
                    str.append(lastName);
                }
                return str.toString();
            });
        } catch (EmptyResultDataAccessException e) {
            return "User";
        }
    }
}
