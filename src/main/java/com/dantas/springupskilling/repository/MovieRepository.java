package com.dantas.springupskilling.repository;

import com.dantas.springupskilling.entity.Movie;
import com.dantas.springupskilling.projection.MovieInfoMinProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query(nativeQuery = true,
            value = "SELECT m.id, m.name FROM movies m INNER JOIN  genres g ON g.id = m.id_genres " +
                    "WHERE g.description = :genreDescription")
    public List<MovieInfoMinProjection> search1SQL(String genreDescription);

    @Query("SELECT new com.dantas.springupskilling.dto.MovieDto(m.id,m.name) " +
            "FROM Movie m WHERE m.genre.description = :genreDescription")
    public List<MovieInfoMinProjection> search2JPQL(String genreDescription);
}
