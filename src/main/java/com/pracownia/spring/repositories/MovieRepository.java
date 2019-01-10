package com.pracownia.spring.repositories;

import com.pracownia.spring.entities.Movie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
public interface MovieRepository extends CrudRepository<Movie, Integer>, PagingAndSortingRepository<Movie, Integer> {

    @Query("select count(*) from Movie m where m.id = ?1")
    Integer checkIfExist(Integer id);
}
