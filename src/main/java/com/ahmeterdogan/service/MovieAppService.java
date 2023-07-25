package com.ahmeterdogan.service;

import com.ahmeterdogan.data.entity.Movie;
import com.ahmeterdogan.data.repository.MovieRepository;
import com.ahmeterdogan.util.JDBCUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieAppService {
    private final MovieRepository movieAppRepo;

    public MovieAppService(MovieRepository movieAppRepo) {
        this.movieAppRepo = movieAppRepo;
    }

    public Iterable<Movie> findAllMovies() {
        return movieAppRepo.findAllMovies();
    }


    public Optional<Movie> findMovieByName(String movieName) {
        return movieAppRepo.findMovieByName(movieName);
    }

    public Optional<Movie> findMovieById(long id) {
       return movieAppRepo.findMovieById(id);
    }

    public Iterable<Movie> findMovieByDirector(String directorName) {
        return movieAppRepo.findMovieByDirector(directorName);
    }

    public long save(Movie movie) {
        return movieAppRepo.save(movie);
    }
}
