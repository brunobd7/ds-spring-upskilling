package com.dantas.springupskilling.projections;

/**
 * Projection for {@link com.dantas.springupskilling.entities.User}
 */
public interface UserDetailProjection {

    String getUsername();
    String getPassword();
    Long getRoleId();
    String getAuthority();

}