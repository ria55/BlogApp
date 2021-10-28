package app.services;

import app.models.AppUser;
import app.models.Blog;
import app.models.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    /* tested count() method from UserService
        commented because should refactor

    @Autowired
    UserService service;

    @Test
    void count() {
        //addTestUsers();

        long count = service.count(AppUser.class);
        assertEquals(9, count);

        long count2 = service.count(Blog.class);
        assertEquals(0, count2);
    }

    private void addTestUsers() {
        AppUser user = new AppUser("Marcipán", "kiscica", "kiscica", UserRole.ADMIN);
        AppUser user2 = new AppUser("Kolompér", "kiscica", "kiscica", UserRole.ADMIN);
        AppUser user3 = new AppUser("Cseresznye", "kiscica", "kiscica", UserRole.ADMIN);
        service.registerUser(user);
        service.registerUser(user2);
        service.registerUser(user3);
    }*/

}