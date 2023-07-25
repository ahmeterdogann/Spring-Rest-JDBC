package com.ahmeterdogan.controller;

import com.ahmeterdogan.data.entity.Movie;
import com.ahmeterdogan.service.MovieAppService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/movies")
public class MovieAppController {
    private final MovieAppService movieAppService;

    public MovieAppController(MovieAppService movieAppService) {
        this.movieAppService = movieAppService;
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Movie> findAll() {
        return movieAppService.findAllMovies();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Movie findById(@PathVariable long id) {
        //http://localhost:8080/MovieApp/api/movies/all/1
        return  movieAppService.findMovieById(id).orElse(null);
    }

    @GetMapping(value = "/director", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Movie> findMovieByDirectorName(@RequestParam("director") String directorName) {
        //http://localhost:8080/MovieApp/api/movies/director?director=Quentin Tarantino
        return movieAppService.findMovieByDirector(directorName);
    }

    @GetMapping(value = "/name", produces = MediaType.APPLICATION_JSON_VALUE)
    public Movie findMovieByName(@RequestParam("name") String movieName) {
        //http://localhost:8080/MovieApp/api/movies/name?name=Pulp Fiction
        return movieAppService.findMovieByName(movieName).orElse(null);
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public long save(@RequestBody Movie movie) {
        return movieAppService.save(movie);
    }

}
