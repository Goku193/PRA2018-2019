package com.pracownia.spring.services;

import com.pracownia.spring.entities.Director;
import com.pracownia.spring.entities.Movie;
import com.pracownia.spring.repositories.MovieRepository;
import com.pracownia.spring.repositories.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DirectorServiceImpl implements DirectorService {

    @Autowired
    private DirectorRepository directorRepository;

    @Override
    public Iterable<Director> listAllDirectors() {
        return directorRepository.findAll();
    }

    @Override
    public Director getDirectorbyId(Integer id) {
        return directorRepository.findOne(id);
    }

    @Override
    public Director saveDirector(Director director) {
        return directorRepository.save(director);
    }

    @Override
    public void deleteDirector(Integer id) {
        directorRepository.delete(id);
    }

    @Override
    public List<Director> getByName(String name) {
        return directorRepository.findByName(name);
    }

    @Override
    public Integer getNumberofFilms(Integer id) {
        return directorRepository.countMoviesById(id);
    }


}
