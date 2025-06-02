package com.dantas.springupskilling;

import com.dantas.springupskilling.projection.MovieInfoMinProjection;
import com.dantas.springupskilling.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class DsSpringUpskillingApplication implements CommandLineRunner {

    @Autowired
    private MovieRepository movieRepository;

    public static void main(String[] args) {
        SpringApplication.run(DsSpringUpskillingApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<MovieInfoMinProjection> movies  = movieRepository.search1SQL("Action");

        System.out.println("********* SQL QUERY RESULT ********* ");
        movies.forEach(movie -> System.out.println(movie.getName()));

        System.out.println("\n\n ********* JPQL QUERY RESULT ********* ");
        movies = movieRepository.search2JPQL("Action");
        movies.forEach(movie -> System.out.println(movie.getName()));

    }
}
