package com.pracownia.spring.entities;


import javax.persistence.*;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Movie {
    @Id
    @GeneratedValue(generator = "genMov")
    @SequenceGenerator(name = "genMov", sequenceName = "movie_seq")
    @Column(name="id")
    private int Id;

    @Column
    private String title;

    @Column
    private String year;


    public Movie()
    {

    }

    public Movie(String title, String year)
    {
        this.title = title;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}
