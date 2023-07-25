package com.ahmeterdogan.data.repository;

import com.ahmeterdogan.data.entity.Movie;
import com.ahmeterdogan.util.JDBCUtil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class MovieRepository {
    private static final String FIND_ALL_SQL = "select * from movies";
    private static final String FIND_MOVIE_BY_NAME_SQL = "select * from movies where name = ?";
    private static final String FIND_MOVIE_BY_ID_SQL = "select * from movies where id = ?";
    private static final String FIND_MOVIE_BY_DIRECTOR_SQL = "select * from movies m  where m.director = ?";
    private static final String SAVE_SQL = "insert into movies(name, year, director) values (?, ?, ?)";


    private final JdbcTemplate jdbcTemplate;

    public MovieRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public class MovieRowMapper implements RowMapper<Movie> {
        @Override
        public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {
            var id = rs.getLong(1);
            var name = rs.getString(2);
            var year = rs.getString(3);
            var director = rs.getString(4);

            return new Movie(id, name, year, director);
        }
    }

    public Iterable<Movie> findAllMovies() {
        return jdbcTemplate.query(FIND_ALL_SQL, new MovieRowMapper());
    }

    public Optional<Movie> findMovieByName(String movieName) {
        var movie = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_MOVIE_BY_NAME_SQL, new MovieRowMapper(), JDBCUtil.getArguments(movieName)));

        return movie.isEmpty() ? Optional.empty() : movie;
    }

    public Optional<Movie> findMovieById(long id) {
        var movie = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_MOVIE_BY_ID_SQL, new MovieRowMapper(), JDBCUtil.getArguments(id)));

        return movie.isEmpty() ? Optional.empty() : movie;
    }

    public Iterable<Movie> findMovieByDirector(String directorName) {
        return jdbcTemplate.query(FIND_MOVIE_BY_DIRECTOR_SQL, new MovieRowMapper(), JDBCUtil.getArguments(directorName));
    }

    public long save(Movie movie) {
        SimpleJdbcInsert simpleJdbcInsert =
                new SimpleJdbcInsert(jdbcTemplate.getDataSource()).withTableName("movies").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("name", movie.getName());
        parameters.put("year", movie.getYear());
        parameters.put("director", movie.getDirector());

        var id = simpleJdbcInsert.executeAndReturnKey(parameters);
        System.out.println("Generated id -> " + id);

        return id.longValue();
    }


}
