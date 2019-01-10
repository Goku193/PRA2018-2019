package com.pracownia.spring.services;
import com.pracownia.spring.entities.Movie;
import java.util.Optional;


public interface MovieService {

    Iterable<Movie> listAllMovies();

    Movie getMovieById(Integer id);

    Movie saveMovie(Movie movie);

    void deleteMovie(Integer id);

    Boolean checkIfExist(Integer id);

    public Iterable<Movie> listAllProductsPaging(Integer pageNr, Integer howManyOnPage);
}
