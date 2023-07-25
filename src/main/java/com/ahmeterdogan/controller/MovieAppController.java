package com.ahmeterdogan.controller;

import com.ahmeterdogan.data.entity.Movie;
import com.ahmeterdogan.service.MovieAppService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/movies")
public class MovieAppController {
    private final MovieAppService movieAppService;

    public MovieAppController(MovieAppService movieAppService) {
        this.movieAppService = movieAppService;
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Movie>> findAll() {
        return ResponseEntity.ok(movieAppService.findAllMovies());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Movie> findById(@PathVariable long id) {
        //http://localhost:8080/MovieApp/api/movies/all/1
        return  ResponseEntity.ok(movieAppService.findMovieById(id).orElse(null));
    }

    @GetMapping(value = "/director", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Movie>> findMovieByDirectorName(@RequestParam("director") String directorName) {
        //http://localhost:8080/MovieApp/api/movies/director?director=Quentin Tarantino
        return ResponseEntity.ok(movieAppService.findMovieByDirector(directorName));
    }

    @GetMapping(value = "/name", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Movie> findMovieByName(@RequestParam("name") String movieName) {
        //http://localhost:8080/MovieApp/api/movies/name?name=Pulp Fiction
        return ResponseEntity.ok(movieAppService.findMovieByName(movieName).orElse(null));
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> save(@RequestBody Movie movie) {
        return ResponseEntity.ok(movieAppService.save(movie));
    }

}
