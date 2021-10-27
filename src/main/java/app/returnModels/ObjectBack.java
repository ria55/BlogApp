package app.returnModels;

import org.springframework.http.HttpStatus;

public class ObjectBack<T> extends Feedback {

    private T object;

    public ObjectBack() {}

    public ObjectBack(boolean success, HttpStatus status) {
        super(success, status);
    }

    public ObjectBack(T object) {
        super(true, HttpStatus.OK);
        this.object = object;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

}
