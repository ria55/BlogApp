package app.services;

import app.dtos.AppUserDTO;
import app.dtos.PasswordResetDTO;
import app.models.AppUser;
import app.models.UserRole;
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
import java.util.ArrayList;
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

    public AppUser getLoggedInUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            Object user = auth.getPrincipal();
            if (user instanceof AppUser) {
                return (AppUser) user;
            }
        }

        return null;
    }

    @Transactional
    public List<AppUserDTO> listAllUsers() {
        try {
            List<AppUser> users = findAllUsers();
            List<AppUserDTO> usersDTO = new ArrayList<>();

            for (AppUser user : users) {
                usersDTO.add(new AppUserDTO(user));
            }

            return usersDTO;
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    protected List<AppUser> findAllUsers() {
        return em.createQuery("SELECT user FROM AppUser user", AppUser.class)
                .getResultList();
    }

    @Transactional
    public Feedback registerUser(AppUserDTO user) {
        if (!isUsernameExisting(user.getUsername())) {
            registerUser(new AppUser(user, UserRole.USER));

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
    public boolean changeForgottenPassword(PasswordResetDTO pwReset) {
        try {
            AppUser userFromDB = (AppUser) loadUserByUsername(pwReset.getUsername());
            String newPassword = pwReset.getNewPassword();
            userFromDB.setPassword(encoder.encode(newPassword));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public boolean changeLoggedInUserPassword(String newPassword) {
        AppUser user = getLoggedInUser();

        if (user != null) {
            return changeForgottenPassword(new PasswordResetDTO(user.getUsername(), null, newPassword));

        }

        return false;
    }

    @Transactional
    public boolean changeRole(AppUser user, UserRole newRole) {
        try {
            AppUser userFromDB = (AppUser) loadUserByUsername(user.getUsername());
            userFromDB.setUserRole(newRole);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
