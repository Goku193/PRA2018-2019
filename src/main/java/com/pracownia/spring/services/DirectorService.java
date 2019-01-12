package com.pracownia.spring.services;

import com.pracownia.spring.entities.Movie;
import com.pracownia.spring.entities.Director;
import com.pracownia.spring.controllers.DirectorController;

import java.util.List;


public interface DirectorService {

    Iterable<Director> listAllDirectors();

    Director getDirectorbyId(Integer id);

    Director saveDirector(Director director);

    void deleteDirector(Integer id);

    List<Director> getByName(String name);

    Integer getNumberofFilms(Integer id);

}
