package com.ahmeterdogan.data.repository;

import com.ahmeterdogan.data.entity.Director;
import com.ahmeterdogan.data.entity.Movie;
import com.ahmeterdogan.util.JDBCUtil;
import com.sun.source.tree.IdentifierTree;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MovieRepository {
    private static final String FIND_ALL_SQL = "select * from movies";
    private static final String FIND_MOVIE_BY_NAME = "select * from movies where name = ?";
    private static final String FIND_MOVIE_BY_ID = "select * from movies where id = ?";
    private static final String FIND_MOVIE_BY_DİRECTOR = "select * from movies m inner join director d on d.id = m.director where concat(d.name, ' ', d.lastname)  ilike ?";


    private final JdbcTemplate jdbcTemplate;
    private final  DirectorRepository directorRepository;

    public MovieRepository(JdbcTemplate jdbcTemplate, DirectorRepository directorRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.directorRepository = directorRepository;
    }

    public class MovieRowMapper implements RowMapper<Movie> {
        @Override
        public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {
            var id = rs.getLong(1);
            var name = rs.getString(2);
            var year = rs.getString(3);
            var director = directorRepository.findById(rs.getLong(4));

            return new Movie(id, name, year, director.orElse(null));
        }
    }

    public Iterable<Movie> findAllMovies() {
        return jdbcTemplate.query(FIND_ALL_SQL, new MovieRowMapper());
    }

    public Optional<Movie> findMovieByName(String movieName) {
        var movie = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_MOVIE_BY_NAME, new MovieRowMapper(), JDBCUtil.getArguments(movieName)));

        return movie.isEmpty() ? Optional.empty() : movie;
    }

    public Optional<Movie> findMovieById(long id) {
        var movie = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_MOVIE_BY_ID, new MovieRowMapper(), JDBCUtil.getArguments(id)));

        return movie.isEmpty() ? Optional.empty() : movie;
    }

    public Iterable<Movie> findMovieByDirector(String directorName) {
        return jdbcTemplate.query(FIND_MOVIE_BY_DİRECTOR, new MovieRowMapper(), JDBCUtil.getArguments(directorName));
    }



}
