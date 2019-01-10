package com.pracownia.spring.controllers;

import com.pracownia.spring.entities.Movie;
import com.pracownia.spring.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Movie controller.
 */

@RestController
@RequestMapping("/api")

public class MovieController {
    @Autowired
    private MovieService movieService;

    /**
     * List all movies.
     */
    @RequestMapping(value = "/movies", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Movie> list(Model model) {
        return movieService.listAllMovies();
    }

    // Only for redirect!
    @ApiIgnore
    @RequestMapping(value = "/movies", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Movie> redirect(Model model) {
        return movieService.listAllMovies();
    }

    @RequestMapping(value = "/movies/{page}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Movie> list(@PathVariable("page") Integer pageNr, @RequestParam("size") Optional<Integer> howManyOnPage) {
        return movieService.listAllProductsPaging(pageNr, howManyOnPage.orElse(2));
    }

    /**
     * View a specific movie by its id.
     */
    @RequestMapping(value = "/movie/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Movie getByPublicId(@PathVariable("id") Integer publicId) {
        return movieService.getMovieById(publicId);
    }

    /**
     * Save movie to database.
     */
    @RequestMapping(value = "/movie", method = RequestMethod.POST)
    public ResponseEntity<Movie> create(@RequestBody @Valid @NotNull Movie movie) {
        movieService.saveMovie(movie);
        return ResponseEntity.ok().body(movie);
    }

    /**
     * Edit movie in database.
     */
    @RequestMapping(value = "/movie", method = RequestMethod.PUT)
    public ResponseEntity<Void> edit(@RequestBody @Valid @NotNull Movie movie) {
        if (!movieService.checkIfExist(movie.getId()))
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else {
            movieService.saveMovie(movie);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    /**
     * Delete movie by its id.
     */
    @RequestMapping(value = "/movie/{id}", method = RequestMethod.DELETE)
    public RedirectView delete(HttpServletResponse response, @PathVariable Integer id) {
        movieService.deleteMovie(id);
        return new RedirectView("/api/products", true);
    }

}


