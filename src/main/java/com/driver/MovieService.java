package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public String addDirectorToDB(Director director){

        String name = director.getName();
        movieRepository.directorDB.put(name,director);
        return "Director added successfully";
    }

    public String addMovieToDB(Movie movie){

        String name = movie.getName();
        movieRepository.movieDB.put(name,movie);
        return "Movie added successfully";
    }

    public String addPairToDB(String movieName, String directorName) {

        if(movieRepository.movieDB.containsKey(movieName)&& movieRepository.directorDB.containsKey(directorName)){

            if(!movieRepository.dirMovPair.containsKey(directorName)){
                List<String> movieList = new ArrayList<>();
                movieList.add(movieName);
                movieRepository.dirMovPair.put(directorName,movieList);
            }
            else{
                List<String> movieList = movieRepository.dirMovPair.get(directorName);
                movieList.add(movieName);
                movieRepository.dirMovPair.put(directorName,movieList);
            }
            return "Existing Movie and Director Pair added successfully";
        }
        return "The parameters doesn't exist in Database";
    }

    public Movie getMovieByName(String movieName){

        if(movieRepository.movieDB.containsKey(movieName)){
            return movieRepository.movieDB.get(movieName);
        }
        return null;
    }

    public Director getDirectorByName(String dirName){

        if(movieRepository.directorDB.containsKey(dirName)) {
            return movieRepository.directorDB.get(dirName);
        }
        return null;
    }

    public List<String> getMoviesList(String dirName){

        if(movieRepository.dirMovPair.containsKey(dirName)){
            return movieRepository.dirMovPair.get(dirName);
        }
        return null;
    }

    public List<String> getAllMovies() {

        List<String> movieList = new ArrayList<>();
        for(String name: movieRepository.movieDB.keySet()){
            movieList.add(name);
        }
        return movieList;
    }

    public String deleteDirFromDb(String dirName) {

        if(movieRepository.directorDB.containsKey(dirName)){
            if(movieRepository.dirMovPair.containsKey(dirName)) {
                List<String> movieList = movieRepository.dirMovPair.get(dirName);
                for (String movie : movieList) {
                    if (movieRepository.movieDB.containsKey(movie)) {
                        movieRepository.movieDB.remove(movie);

                    }
                }
                movieRepository.directorDB.remove(dirName);
                movieRepository.dirMovPair.remove(dirName);
                return "Director removed successfully";
            }
        }
        return "Director doesn't exist in Database";
    }

    public String deleteAllDirectorsFromDb() {

        for(String dirName: movieRepository.directorDB.keySet()){
            if(movieRepository.dirMovPair.containsKey(dirName)){
                List<String> movies = movieRepository.dirMovPair.get(dirName);
                for(String movie : movies){
                    if(movieRepository.movieDB.containsKey(movie)){
                        movieRepository.movieDB.remove(movie);
                    }
                }
            }
        }
        movieRepository.dirMovPair.clear();
        movieRepository.directorDB.clear();
        return "All Directors deleted";
    }
}
