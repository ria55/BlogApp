package app.controllers;

import app.models.AppUser;
import app.returnModels.Feedback;
import app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
public class UserController {

    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public Feedback registerUser(@RequestBody AppUser user) {
        return service.register(user);
    }

    @Transactional
    @GetMapping("/user")
    public Feedback getLoggedInUser() {
        return service.getLoggedInUser();
    }

    @PutMapping("/user")
    public Feedback changePassword() {
        return new Feedback(false, HttpStatus.BAD_GATEWAY);
    }

    @PreAuthorize("hasAuthority('READ_ALL')")
    @GetMapping("/users/{id}")
    public Feedback getOneUser(@PathVariable("id") Integer id) {
        return new Feedback(false, HttpStatus.BAD_GATEWAY);
    }

    @PreAuthorize("hasAuthority('READ_ALL')")
    @GetMapping("/users")
    public Feedback listAllUsers() {
        return new Feedback(false, HttpStatus.BAD_GATEWAY);
    }

}
