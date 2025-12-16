package com.dantas.springupskilling.services;

import com.dantas.springupskilling.dto.UserDTO;
import com.dantas.springupskilling.services.exceptions.ForbidenException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;

    protected void selfOrAdminValidation(Long userId) {

        UserDTO me = userService.getMe();
        if(!me.getRoles().contains("ROLE_ADMIN") && !me.getId().equals(userId)) {
            throw new ForbidenException("Access Denied ! You don't have permission to access this resource");
        }

    }

}
