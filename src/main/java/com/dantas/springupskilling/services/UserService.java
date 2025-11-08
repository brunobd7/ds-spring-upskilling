package com.dantas.springupskilling.services;

import com.dantas.springupskilling.entities.Role;
import com.dantas.springupskilling.entities.User;
import com.dantas.springupskilling.projections.UserDetailProjection;
import com.dantas.springupskilling.repositories.UserRepository;
import com.dantas.springupskilling.services.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

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
}
