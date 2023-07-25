package com.ahmeterdogan.data.repository;

import com.ahmeterdogan.data.entity.Director;
import com.ahmeterdogan.data.entity.Movie;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MovieRepository {
    private static final String FIND_ALL_SQL = "select * from movies";

    private final JdbcTemplate jdbcTemplate;
    private final  DirectorRepository directorRepository;

    public MovieRepository(JdbcTemplate jdbcTemplate, DirectorRepository directorRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.directorRepository = directorRepository;
    }

    private void fillMovies(ResultSet resultSet, List<Movie> movies) {
        try {
            do {
                var id = resultSet.getLong(1);
                var name = resultSet.getString(2);
                var year = resultSet.getString(3);
                var directorId = resultSet.getLong(4);

                movies.add(new Movie(id, name, year, directorRepository.findById(directorId).get()));
            }
            while (resultSet.next());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public List<Movie> findAllMovies() {
        var movies = new ArrayList<Movie>();

        jdbcTemplate.query(FIND_ALL_SQL, (ResultSet rs) -> fillMovies(rs, movies));

        return movies;

    }

}
