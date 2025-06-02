package com.dantas.springupskilling.projection;

import com.dantas.springupskilling.entity.Movie;

/**
 * Projection for {@link Movie}
 */
public interface MovieInfoMinProjection {
    Long getId();

    String getName();
}