package tech.overpass.conferauth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tech.overpass.conferauth.model.db.Role;
import tech.overpass.conferauth.model.db.User;
import tech.overpass.conferauth.model.dto.UserRegistrationDto;
import tech.overpass.conferauth.repository.UserRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class ForumUserDetailsServiceImpl implements ForumUserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User save(UserRegistrationDto registration) {
        User user = new User();
        user.setUsername(registration.getUsername());
        user.setFullName(registration.getFullName());
        user.setPasswordHash(passwordEncoder.encode(registration.getPassword()));
        user.setEmail(registration.getEmail());
        user.setRoles(Collections.singletonList(new Role("ROLE_USER")));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username not found");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPasswordHash(),
                mapRolesToAuthorities(user.getRoles())
        );
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

}
