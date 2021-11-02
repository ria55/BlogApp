package app.dtos;

import app.models.AppUser;
import app.models.UserRole;

public class AppUserDTO {

    private String username;
    private String nickname;
    private String password;
    private UserRole role;

    public AppUserDTO() {}

    public AppUserDTO(String username, String nickname) {
        this.username = username;
        this.nickname = nickname;
    }

    public AppUserDTO(String username, String nickname, String password) {
        this(username, nickname);
        this.password = password;
    }

    public AppUserDTO(String username, String nickname, String password, UserRole role) {
        this(username, nickname, password);
        this.role = role;
    }

    public AppUserDTO(AppUser appUser) {
        this(appUser.getUsername(), appUser.getNickName());
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

}
