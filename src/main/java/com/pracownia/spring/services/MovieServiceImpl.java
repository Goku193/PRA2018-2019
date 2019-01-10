package com.pracownia.spring.services;

import com.pracownia.spring.entities.Movie;
import com.pracownia.spring.repositories.MovieRepository;
import com.pracownia.spring.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Iterable<Movie> listAllProductsPaging(Integer pageNr, Integer howManyOnPage) {
        return movieRepository.findAll(new PageRequest(pageNr,howManyOnPage));
    }

    @Override
    public Iterable<Movie> listAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie getMovieById(Integer id) {
        return movieRepository.findOne(id);
    }

    @Override
    public Movie saveMovie(Movie product) {
        return movieRepository.save(product);
    }

    @Override
    public void deleteMovie(Integer id) {
        movieRepository.delete(id);
    }

    @Override
    public Boolean checkIfExist(Integer id) {
        if (movieRepository.checkIfExist(id) > 0)
            return true;
        else
            return false;
    }

}

