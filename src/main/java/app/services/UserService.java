package app.services;

import app.models.AppUser;
import app.returnModels.Feedback;
import app.returnModels.ObjectBack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService extends ServiceBase implements UserDetailsService {

    private PasswordEncoder encoder;

    @Autowired
    public UserService(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return em.createQuery(
                "SELECT user FROM AppUser user WHERE user.username = :name",
                AppUser.class)
                .setParameter("name", username)
                .getSingleResult();
    }

    @Transactional
    protected boolean isUsernameExisting(String username) {
        try {
            loadUserByUsername(username);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Feedback getLoggedInUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            Object user = auth.getPrincipal();
            if (user instanceof AppUser) {
                return new ObjectBack<>((AppUser) user);
            }
        }

        return new Feedback(false, HttpStatus.BAD_REQUEST, "no user logged in");
    }

    @Transactional
    public List<AppUser> listAllUsers() {
        return em.createQuery("SELECT user FROM AppUser user", AppUser.class)
                .getResultList();
    }

    @Transactional
    public Feedback register(AppUser user) {
        if (!isUsernameExisting(user.getUsername())) {
            registerUser(user);

            try {
                AppUser registered = (AppUser) loadUserByUsername(user.getUsername());
                return new ObjectBack<>(registered);
            } catch (Exception e) {
                return new Feedback(false, HttpStatus.BAD_REQUEST, e.getMessage());
            }
        }
        System.out.println("username taken");
        return new Feedback(false, HttpStatus.BAD_REQUEST, "username taken");

    }

    @Transactional
    public void registerUser(AppUser user) {
        user.setPassword(encoder.encode(user.getPassword()));
        em.persist(user);
    }

    @Transactional
    public boolean changePassword(AppUser user) {
        try {
            AppUser userFromDB = (AppUser) loadUserByUsername(user.getUsername());
            userFromDB.setPassword(user.getPassword());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
