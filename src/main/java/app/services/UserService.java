package app.services;

import app.jpaRepos.UserRepo;
import app.models.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @PersistenceContext
    private EntityManager em;

    private UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = em.createQuery(
                "SELECT user FROM AppUser user WHERE user.username = :name",
                AppUser.class)
                .setParameter("name", username)
                .getSingleResult();

        // em.find(AppUser.class, username);

        return user;
    }

    @Transactional
    public List<AppUser> listAllUsers() {
        List<AppUser> users = em.createQuery("SELECT user FROM AppUser user", AppUser.class)
                .getResultList();

        List<AppUser> users2 = userRepo.findAll();

        return users;
    }

    @Transactional
    public void registerUser(AppUser user) {
        userRepo.save(user);
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
