package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    @Autowired
    MovieService movieService;
    @PostMapping("/movies/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody() Movie movie) {

        String response = movieService.addMovieToDB(movie);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/movies/add-director")
    public ResponseEntity<String> addDirector(@RequestBody() Director director) {

        String response = movieService.addDirectorToDB(director);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/movies/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam("movieName") String movieName,@RequestParam("directorName") String directorName) {

        String response = movieService.addPairToDB(movieName, directorName);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("movies/get-movie-by-name")
    public ResponseEntity<Movie> getMovieByName(@RequestParam("movieName")String movieName) {

        Movie movie = movieService.getMovieByName(movieName);
        return new ResponseEntity<>(movie, HttpStatus.FOUND);
    }

    @GetMapping("movies/get-director-by-name")
    public ResponseEntity<Director> getDirectorByName(@RequestParam("directorName")String directorName) {

        Director director = movieService.getDirectorByName(directorName);
        return new ResponseEntity<>(director, HttpStatus.FOUND);
    }

    @GetMapping("/movies/get-movies-by-director-name")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@RequestParam("directorName")String directorName) {

        List<String> movieList = movieService.getMoviesList(directorName);
        return new ResponseEntity<>(movieList, HttpStatus.OK);
    }

    @GetMapping("/movies/get-all-movies")
    public ResponseEntity<List<String>> findAllMovies() {

        List<String> movieList = movieService.getAllMovies();
        return new ResponseEntity<>(movieList, HttpStatus.OK);
    }

    @DeleteMapping("/movies/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam("directorName")String directorName) {

        String response = movieService.deleteDirFromDb(directorName);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/movies/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors() {

        String response = movieService.deleteAllDirectorsFromDb();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
