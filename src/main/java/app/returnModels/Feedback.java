package app.returnModels;

import org.springframework.http.HttpStatus;

public class Feedback {

    private boolean success;
    private HttpStatus status;

    private String message;

    public Feedback() {}

    /**
     * This constructor is only for testing.
     */
    public Feedback(String json) {
        String[] keyValues = json.split(",");

        for (String keyValue : keyValues) {
            String[] split = keyValue.split(":");
            String value = split[1].replace("\"", "");

            if (split[0].contains("success")) {
                success = Boolean.parseBoolean(split[1]);
            } else if (split[0].contains("status")) {
                status = HttpStatus.valueOf(value);
            } else {
                message = value;
            }
        }
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
