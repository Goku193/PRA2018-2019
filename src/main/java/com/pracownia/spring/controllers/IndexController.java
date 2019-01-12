package com.pracownia.spring.controllers;

import com.pracownia.spring.entities.Director;
import com.pracownia.spring.entities.Product;
import com.pracownia.spring.entities.Seller;
import com.pracownia.spring.entities.Movie;
import com.pracownia.spring.services.DirectorService;
import com.pracownia.spring.services.MovieService;
import com.pracownia.spring.services.ProductService;
import com.pracownia.spring.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Homepage controller.
 */
@RestController
@RequestMapping("/")
public class IndexController {

    @Autowired
    private ProductService productService;

    @Autowired
    private SellerService sellerService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private DirectorService directorService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    String index() {
        return "index";
    }


    @RequestMapping(value = "generateModel", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
    public String generateModel() {

        LocalDateTime localDateAndTime = LocalDateTime.now();
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime dateAndTime  = ZonedDateTime.of(localDateAndTime, zoneId);

        Product p1 = new Product(UUID.randomUUID().toString(),"Jajko", new BigDecimal(23.50), dateAndTime.plusDays(7));
        Product p2 = new Product(UUID.randomUUID().toString(),"Masło", new BigDecimal(3.50), dateAndTime.plusDays(7));
        Product p3 = new Product(UUID.randomUUID().toString(),"Mąka", new BigDecimal(1.50), dateAndTime.plusDays(7));

        productService.saveProduct(p1);
        productService.saveProduct(p2);
        productService.saveProduct(p3);

        Seller seller = new Seller("Biedra", "Poznan", Arrays.asList(p1.getProductId(), p2.getProductId(), p3.getProductId()));
        Seller seller2 = new Seller("Lidl", "Krosno", Arrays.asList(p1.getProductId(), p2.getProductId()));

        sellerService.saveSeller(seller);
        sellerService.saveSeller(seller2);

        p1.getSellers().add(seller);
        p2.getSellers().add(seller);
        p3.getSellers().add(seller);
        p1.getSellers().add(seller2);
        p2.getSellers().add(seller2);

        productService.saveProduct(p1);
        productService.saveProduct(p2);
        productService.saveProduct(p3);

        Movie m1 = new Movie("Captain Marvel","2019");
        Movie m2 = new Movie("Avengers: Infinity War", "2018");
        Movie m3 = new Movie ("Captain America: Civil War", "2016");

        movieService.saveMovie(m1);
        movieService.saveMovie(m2);
        movieService.saveMovie(m3);

        Director director1 = new Director("Anthony", "Russo", Arrays.asList(m2.getMovieId(), m3.getMovieId()));
        Director director2 = new Director("Joe", "Russo", Arrays.asList(m2.getMovieId(),m3.getMovieId()));
        Director director3 = new Director ("Anna", "Boden", Arrays.asList(m1.getMovieId()));

        directorService.saveDirector(director1);
        directorService.saveDirector(director2);
        directorService.saveDirector(director3);

        m2.getDirectors().add(director1);
        m2.getDirectors().add(director2);
        m1.getDirectors().add(director3);
        m3.getDirectors().add(director1);
        m3.getDirectors().add(director2);


        movieService.saveMovie(m1);
        movieService.saveMovie(m2);
        movieService.saveMovie(m3);


        return "Model Generated";
    }

}
