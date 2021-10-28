package app.returnModels;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ListBack<T> extends Feedback {

    private List<T> list;

    public ListBack() {}

    public ListBack(boolean success, HttpStatus status) {
        super(success, status);
    }

    public ListBack(List<T> list) {
        super(true, HttpStatus.OK);
        this.list = list;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

}
