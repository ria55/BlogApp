package app.dtos;

public class PasswordResetDTO {

    private String username;
    private String token;
    private String newPassword;

    public PasswordResetDTO() {}

    public PasswordResetDTO(String username, String token, String newPassword) {
        this.username = username;
        this.token = token;
        this.newPassword = newPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

}
