package tech.overpass.conferauth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.overpass.conferauth.exception.RegistrationFailedException;
import tech.overpass.conferauth.model.db.User;
import tech.overpass.conferauth.model.dto.UserDto;
import tech.overpass.conferauth.service.ForumUserDetailsService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/auth")
public class ApiAuthController {

    private final ForumUserDetailsService userDetailsService;

    @Autowired
    public ApiAuthController(ForumUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity register(@RequestBody UserDto userDto, @Autowired HttpServletRequest request) {
        User existing = userDetailsService.findByEmail(userDto.getEmail());
        if (existing != null) {
            throw new RegistrationFailedException("There is already an account registered with that email");
        }
        userDetailsService.save(userDto.toUserRegistrationDto());
        return ResponseEntity.ok().build();
    }

}
