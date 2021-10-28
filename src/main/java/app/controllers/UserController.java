package app.controllers;

import app.returnModels.Feedback;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @PostMapping("/register")
    public Feedback registerUser() {
        return new Feedback(false, HttpStatus.BAD_GATEWAY);
    }

    @GetMapping("/user")
    public Feedback getLoggedInUser() {
        return new Feedback(false, HttpStatus.BAD_GATEWAY);
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
