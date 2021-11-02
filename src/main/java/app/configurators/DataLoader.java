package app.configurators;

import app.dtos.AppUserDTO;
import app.models.AppUser;
import app.models.UserRole;
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

    @Autowired
    public DataLoader(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        addUsers();
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
