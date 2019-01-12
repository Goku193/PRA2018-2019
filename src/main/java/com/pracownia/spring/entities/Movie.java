package com.pracownia.spring.entities;


import javax.persistence.*;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

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
    private String movieId;

    @Column
    private String year;

    @OneToMany(mappedBy = "moviesOb")
    private Set<Director> directors = new HashSet<>();

    public Movie()
    {

    }

    public Movie(String title, String year)
    {
        movieId = getMovieId(title);
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

    public String getMovieId() {return movieId;}

    private String getMovieId(String title) {
        try {
            Document doc = Jsoup.connect("https://www.imdb.com/find?&q=" + title.replace(" ", "+")).get();
            Element table = doc.select("table[class=findList]").first();
            String link = table.select("a").toString();
            movieId = link.substring(16,25);
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return movieId; }

    public void setMovieId(String movieId) { this.movieId = movieId; }

    public Set<Director> getDirectors() {
        return directors;
    }

    public void setDirectors(Set<Director> directors) {
        this.directors = directors;
    }
}
