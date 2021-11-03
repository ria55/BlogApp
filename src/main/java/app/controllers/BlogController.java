package app.controllers;

import app.dtos.BlogDTO;
import app.models.BlogPattern;
import app.returnModels.Feedback;
import app.returnModels.ListBack;
import app.returnModels.ObjectBack;
import app.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BlogController {

    private BlogService service;

    @Autowired
    public BlogController(BlogService service) {
        this.service = service;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/patterns/{name}")
    public Feedback getOnePattern(@PathVariable("name") String name) {
        try {
            BlogPattern pattern = service.findPattern(name);

            return new ObjectBack<>(pattern);
        } catch (Exception e) {
            return new Feedback(false, HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/patterns")
    public Feedback listAllPatterns() {
        try{
            List<BlogPattern> patterns = service.findAllPatterns();

            return new ListBack<>(patterns);
        } catch (Exception e) {
            return new Feedback(false, HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping(value = {"/blogs/{id}", "/blogs/"})
    public Feedback getOneBlog(@PathVariable("id") Long id) {
        if (id != null) {
            BlogDTO blogDTO = service.findBlogById(id);

            if (blogDTO != null) {
                return new ObjectBack<>(blogDTO);
            }
        }

        return new Feedback(false, HttpStatus.BAD_GATEWAY);
    }

    @GetMapping("/blogs")
    public Feedback listAllBlogs() {
        List<BlogDTO> blogs = service.listAllBlogs();

        if (blogs != null) {
            return new ListBack<>(blogs);
        }
        return new Feedback(false, HttpStatus.BAD_GATEWAY);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/blogs")
    public Feedback createBlog(@RequestBody BlogDTO blogDTO) {
        return service.createBlog(blogDTO);
    }

    /*@GetMapping("/blogs/search")
    public Feedback searchForBlog(
            @RequestParam(value = "blog_creator") String creator,
            @RequestParam("blog_name") String blogName) {
        return new Feedback(false, HttpStatus.BAD_GATEWAY);
    }*/

}
