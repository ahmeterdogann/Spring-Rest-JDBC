package com.ahmeterdogan.service;

import com.ahmeterdogan.data.entity.Movie;
import com.ahmeterdogan.data.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieAppService {
    private final MovieRepository movieAppRepo;

    public MovieAppService(MovieRepository movieAppRepo) {
        this.movieAppRepo = movieAppRepo;
    }

    public Iterable<Movie> findAllMovies() {
        return movieAppRepo.findAllMovies();
    }
}
