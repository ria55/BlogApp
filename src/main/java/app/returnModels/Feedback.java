package app.returnModels;

import org.springframework.http.HttpStatus;

public class Feedback {

    private boolean success;
    private HttpStatus status;

    private String message;

    public Feedback() {}

    public Feedback(String message) {
        this.message = message;
    }

    public Feedback(boolean success, HttpStatus status) {
        this.success = success;
        this.status = status;
    }

    public Feedback(boolean success, HttpStatus status, String message) {
        this(success, status);
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
