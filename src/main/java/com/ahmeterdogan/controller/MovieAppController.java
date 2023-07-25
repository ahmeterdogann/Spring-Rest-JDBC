package com.ahmeterdogan.controller;

import com.ahmeterdogan.data.entity.Movie;
import com.ahmeterdogan.service.MovieAppService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/movies")
public class MovieAppController {
    private final MovieAppService movieAppService;

    public MovieAppController(MovieAppService movieAppService) {
        this.movieAppService = movieAppService;
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Movie> findAll() {
        return movieAppService.findAllMovies();
    }
}
