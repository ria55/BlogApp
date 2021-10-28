package app.controllers;

import app.returnModels.Feedback;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class BlogController {

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/patterns/{name}")
    public Feedback getOnePattern(@PathVariable("name") String name) {
        return new Feedback(false, HttpStatus.BAD_GATEWAY);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/patterns")
    public Feedback listAllPatterns() {
        return new Feedback(false, HttpStatus.BAD_GATEWAY);
    }

    @GetMapping("/blogs/{id}")
    public Feedback getOneBlog(@PathVariable("id") Integer id) {
        return new Feedback(false, HttpStatus.BAD_GATEWAY);
    }

    @GetMapping("/blogs")
    public Feedback listAllBlogs() {
        return new Feedback(false, HttpStatus.BAD_GATEWAY);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/blogs")
    public Feedback createBlog() {
        return new Feedback(false, HttpStatus.BAD_GATEWAY);
    }

    @GetMapping("/blogs")
    public Feedback searchForBlog(
            @RequestParam(value = "blog_creator") String creator,
            @RequestParam("blog_name") String blogName) {
        return new Feedback(false, HttpStatus.BAD_GATEWAY);
    }

}
