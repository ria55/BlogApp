package app.models;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class AppUser implements UserDetails {

    @Id
    private String username;

    private String nickName;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @CreationTimestamp
    private LocalDateTime regTime;

    private boolean isLocked;

    @OneToMany(mappedBy = "owner")
    private List<Blog> blogs;

    public AppUser() {
        blogs = new ArrayList<>();
    }

    public AppUser(String username, String nickName, String password, UserRole userRole) {
        this();
        this.username = username;
        this.nickName = nickName;
        this.password = password;
        this.userRole = userRole;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !isLocked;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !isLocked;
    }

    @Override
    public boolean isEnabled() {
        return !isLocked;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userRole.getAuths();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public LocalDateTime getRegTime() {
        return regTime;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void lockUser() {
        setLocked(true);
    }

    public void unlockUser() {
        setLocked(false);
    }

    private void setLocked(boolean locked) {
        if (isLocked != locked) {
            isLocked = locked;
        }
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

}
