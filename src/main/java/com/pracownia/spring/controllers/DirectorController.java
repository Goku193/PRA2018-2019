package com.pracownia.spring.controllers;

import com.pracownia.spring.entities.Movie;
import com.pracownia.spring.entities.Director;
import com.pracownia.spring.services.DirectorService;
import com.pracownia.spring.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api")

public class DirectorController {

    @Autowired
    private DirectorService directorService;

    @RequestMapping(value = "/directors", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Director> list(Model model) {
        return directorService.listAllDirectors();
    }

    // Only for redirect!
    @ApiIgnore
    @RequestMapping(value = "/directors", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Director> redirect(Model model) {
        return directorService.listAllDirectors();
    }

    @RequestMapping(value = "/director/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_ATOM_XML_VALUE)
    public Director getByPublicId(@PathVariable("id") Integer publicId) {
        return directorService.getDirectorbyId(publicId);
    }

    @RequestMapping(value = "/director", method = RequestMethod.POST)
    public ResponseEntity<Director> create(@RequestBody @Valid @NotNull Director director) {
        directorService.saveDirector(director);
        return ResponseEntity.ok().body(director);
    }

    @RequestMapping(value = "/director", method = RequestMethod.PUT)
    public ResponseEntity<Void> edit(@RequestBody @Valid @NotNull Director director) {
        Director sellerFromData = directorService.getDirectorbyId(director.getId());
        if(Objects.nonNull(sellerFromData)) {
            directorService.saveDirector(director);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/director/{id}", method = RequestMethod.DELETE)
    public RedirectView delete(HttpServletResponse response, @PathVariable Integer id) {
        directorService.deleteDirector(id);
        return new RedirectView("/api/directors", true);
    }

    @RequestMapping(value = "/director/{name}", method = RequestMethod.GET)
    public List<Director> getByName(@PathVariable String name) {
        return directorService.getByName(name);
    }

    @RequestMapping(value = "/director/movies/{id}", method = RequestMethod.GET)
    public Integer getMoviesSize(@PathVariable Integer id) {
        return directorService.getNumberofFilms(id);
    }

}
