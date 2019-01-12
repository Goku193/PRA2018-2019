package com.pracownia.spring.entities;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Director {

        @Id
        @GeneratedValue(generator = "genDir")
        @SequenceGenerator(name = "genDir", sequenceName = "director_seq")
        @Column(name="id")
        private int id;

        @Column
        private String name;

        @Column
        private String surname;

        @ElementCollection
        @CollectionTable(name = "movies")
        @Column(name = "movieId")
        private List<String> movies = new ArrayList<>();

        @OneToMany(fetch = FetchType.LAZY)
        private List<Movie> moviesOb;

        public Director()
        {

        }

        public Director(String name, String surname, List <String> movies)
        {
            this.name = name;
            this.surname = surname;
            this.movies = movies;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSurname() {
            return surname;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

        public List<String> getMovies() {
            return movies;
        }

        public void setMovies(List<String> movies) {
            this.movies = movies;
        }
}
