package app.jpaRepos;

import app.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface UserRepo extends JpaRepository<AppUser, String> {
    //AppUser findAppUserByUsername(String username);
    List<AppUser> findAllByAccountNonExpiredAndAuthoritiesBetweenAndRegTimeAfter
    (boolean accountNonExpired, Collection<? extends GrantedAuthority> authorities, Collection<? extends GrantedAuthority> authorities2, LocalDateTime regTime);
}
