package com.ahmeterdogan.data.repository;

import com.ahmeterdogan.data.entity.Director;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class DirectorRepository {
    private static final String FIND_BY_ID = "select * from director where id = ?";

    private final JdbcTemplate jdbcTemplate;

    public DirectorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Director> findById(long id) {
        List<Director> list = jdbcTemplate.query(FIND_BY_ID, getArguments(id), new RowMapper<Director>() {
            @Override
            public Director mapRow(ResultSet rs, int rowNum) throws SQLException {
                var id = rs.getLong(1);
                var name = rs.getString(2);
                var lastname = rs.getString(3);
                var birthdate = rs.getDate(4).toLocalDate();

                return new Director(id, name, lastname, birthdate);
            }
        });

        return list.stream().findFirst();
    }

    private static Object[] getArguments(Object...args) {
        return args;
    }
}
