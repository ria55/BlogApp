package app.controllers;

import app.dtos.AppUserDTO;
import app.dtos.PasswordResetDTO;
import app.models.AppUser;
import app.returnModels.Feedback;
import app.returnModels.ListBack;
import app.returnModels.ObjectBack;
import app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public Feedback registerUser(@RequestBody AppUserDTO user) {
        return service.registerUser(user);
    }

    //@PreAuthorize("isAuthenticated()")
    @GetMapping("/user")
    public Feedback getLoggedInUser() {
        AppUser user = service.getLoggedInUser();
        if (user != null) {
            return new ObjectBack<>(new AppUserDTO(user));
        }
        return new Feedback(false, HttpStatus.UNAUTHORIZED, "no user logged in");
    }

    @PutMapping("/reset-password")
    public Feedback changeForgottenPassword(@RequestBody PasswordResetDTO newPassword) {
        if (service.changeForgottenPassword(newPassword)) {
            return new Feedback(true, HttpStatus.OK, "password changed");
        }
        return new Feedback(false, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/user")
    public Feedback changeLoggedInUserPassword(@RequestBody String newPassword) {
        if (service.changeLoggedInUserPassword(newPassword)) {
            return new Feedback(true, HttpStatus.OK, "password changed");
        }
        return new Feedback(false, HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasAuthority('READ_ALL')")
    @GetMapping(value = {"/users/{username}", "/users/"})
    public Feedback getOneUser(@PathVariable("username") String username) {
        try {
            AppUser user = (AppUser) service.loadUserByUsername(username);
            return new ObjectBack<>(new AppUserDTO(user));
        } catch (Exception e) {
            return new Feedback(false, HttpStatus.BAD_GATEWAY);
        }
    }

    @PreAuthorize("hasAuthority('READ_ALL')")
    @GetMapping("/users")
    public Feedback listAllUsers() {
        List<AppUserDTO> users = service.listAllUsers();

        if (users != null) {
            return new ListBack<>(users);
        }

        return new Feedback(false, HttpStatus.BAD_GATEWAY);
    }

}
