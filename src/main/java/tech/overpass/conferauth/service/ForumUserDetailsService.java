package tech.overpass.conferauth.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import tech.overpass.conferauth.model.db.User;
import tech.overpass.conferauth.model.dto.UserRegistrationDto;

public interface ForumUserDetailsService extends UserDetailsService {
    User findByEmail(String email);

    User save(UserRegistrationDto registration);
}
