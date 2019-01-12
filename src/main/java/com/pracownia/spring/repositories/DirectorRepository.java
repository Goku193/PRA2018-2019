package com.pracownia.spring.repositories;

import com.pracownia.spring.entities.Movie;
import com.pracownia.spring.entities.Director;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DirectorRepository extends CrudRepository<Director, Integer> {

    List<Director> findByName(String name);

    @Query("select count(*) from Director d join d.movies m where d.id = ?1")
    Integer countMoviesById(Integer id);


}
