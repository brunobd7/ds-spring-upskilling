package com.dantas.springupskilling.services;

import com.dantas.springupskilling.dto.UserDTO;
import com.dantas.springupskilling.entities.Role;
import com.dantas.springupskilling.entities.User;
import com.dantas.springupskilling.projections.UserDetailProjection;
import com.dantas.springupskilling.repositories.UserRepository;
import com.dantas.springupskilling.services.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username){

        List<UserDetailProjection> usersList = userRepository.searchUserAndRoleByEmail(username);
        if(usersList.isEmpty()){
            throw new UserNotFoundException("User not found !");
        }

        User userFounded = new User();
        userFounded.setEmail(usersList.getFirst().getUsername());
        userFounded.setPassword(usersList.getFirst().getPassword());

        usersList.forEach(userProjection -> userFounded.addRole(new Role(userProjection.getRoleId(),userProjection.getAuthority())));

        return userFounded;

    }


    @Transactional(readOnly = true)
    public UserDTO getMe(){
        return new UserDTO(authenticated());
    }

    protected User authenticated(){

        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Jwt jwtPrincipal = (Jwt) auth.getPrincipal();
            String username = jwtPrincipal.getClaimAsString("username");

            return userRepository.findByEmail(username).get();
        }catch (Exception e){
            throw new UserNotFoundException("User not found !");
        }
    }
}
