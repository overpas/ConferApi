package tech.overpass.conferauth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tech.overpass.conferauth.model.dto.StrippedUser;
import tech.overpass.conferauth.service.UserService;

@RestController
@RequestMapping("/api/users")
public class ApiUserController {

    private final UserService userService;

    @Autowired
    public ApiUserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public StrippedUser findUserById(@PathVariable("id") long id) {
        return new StrippedUser(userService.findById(id));
    }

}
