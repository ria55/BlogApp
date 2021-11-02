package app.services;

import app.models.AppUser;
import app.models.UserRole;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Arrays;

@TestConfiguration
public class TestConfig {

    @Bean
    @Primary
    public UserDetailsService userDetailsService() {
        AppUser user = createUser("user@user.com", "user", UserRole.USER);
        AppUser admin = createUser("admin@admin.com", "admin", UserRole.ADMIN);

        return new InMemoryUserDetailsManager(Arrays.asList(user, admin));
    }

    private AppUser createUser(String email, String password, UserRole role) {
        AppUser user = new AppUser();
        user.setUsername(email);
        user.setPassword(password);
        user.setUserRole(role);

        return user;
    }

}
