package app.configurators;

import app.models.AppUser;
import app.models.Blog;
import app.models.BlogPattern;
import app.models.UserRole;
import app.services.BlogService;
import app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {

    private UserService userService;
    private BlogService blogService;

    @Autowired
    public DataLoader(UserService userService, BlogService blogService) {
        this.userService = userService;
        this.blogService = blogService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        addUsers();
        AppUser user = (AppUser) userService.loadUserByUsername("bogyo@gmail.com");

        addPattern();
        BlogPattern pattern = blogService.findPattern("Ocean Dream");

        addBlog(user, pattern);
    }

    private void addUsers() {
        Long userCount = userService.count(AppUser.class);

        if (shouldAddRecords(userCount)) {
            List<AppUser> users = createUsers();

            for (AppUser user : users) {
                userService.registerUser(user);
            }
        }
    }

    private void addPattern() {
        Long patternCount = blogService.count(BlogPattern.class);

        if (shouldAddRecords(patternCount)) {
            BlogPattern pattern = new BlogPattern("Ocean Dream", "blue", "white", "Arial");
            blogService.createPattern(pattern);
        }
    }

    private void addBlog(AppUser user, BlogPattern pattern) {
        Long blogCount = blogService.count(Blog.class);

        if (shouldAddRecords(blogCount)) {
            Blog blog = new Blog(user, "My Little Pony", pattern);
            blogService.addBlog(blog);
        }
    }

    private boolean shouldAddRecords(Long count) {
        return (count != null && count == 0);
    }

    private List<AppUser> createUsers() {
        List<AppUser> users = new ArrayList<>();

        users.add(new AppUser("kiscica@gmail.com", "Kiscica", "admin", UserRole.ADMIN));
        users.add(new AppUser("bogyo@gmail.com", "Bogyóka", "moderator", UserRole.MODERATOR));
        users.add(new AppUser("myhero@gmail.com", "Hero", "user", UserRole.USER));

        return users;
    }

}
