package app.controllers;

import app.returnModels.Feedback;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping(value = {"/", "/home"})
    public Feedback getHome() {
        return new Feedback(true, HttpStatus.OK, "Hello, World!");
    }

}
