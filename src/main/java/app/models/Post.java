package app.models;

import app.helpers.DateTimeHelper;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Post {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private Blog releasedBlog;

    private String title;

    private String text;

    @CreationTimestamp
    private LocalDateTime writtenTime;

    @Enumerated//(EnumType.STRING)
    private WritingStatus postStatus;

    @OneToMany(mappedBy = "postTo")
    private List<Comment> comments;

    public Post() {
        writtenTime = DateTimeHelper.getNow();
        comments = new ArrayList<>();
    }

    public Post(Blog releasedBlog, String title, String text) {
        this();
        this.releasedBlog = releasedBlog;
        this.title = title;
        this.text = text;
    }

    public Post(Blog releasedBlog, String title, String text, WritingStatus postStatus) {
        this(releasedBlog, title, text);
        this.postStatus = postStatus;
    }

    public Blog getReleasedBlog() {
        return releasedBlog;
    }

    public void setReleasedBlog(Blog releasedBlog) {
        this.releasedBlog = releasedBlog;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getWrittenTime() {
        return writtenTime;
    }

    public void setWrittenTime(LocalDateTime writtenTime) {
        this.writtenTime = writtenTime;
    }

    public WritingStatus getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(WritingStatus postStatus) {
        this.postStatus = postStatus;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

}
