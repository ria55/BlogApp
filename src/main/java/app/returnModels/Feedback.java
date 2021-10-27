package app.returnModels;

import org.springframework.http.HttpStatus;

public class Feedback {

    private boolean success;
    private HttpStatus status;

    public Feedback() {}

    public Feedback(boolean success, HttpStatus status) {
        this.success = success;
        this.status = status;
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
}
