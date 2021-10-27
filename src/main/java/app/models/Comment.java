package app.models;

import app.helpers.DateTimeHelper;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Comment {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private AppUser author;

    @ManyToOne
    private Post postTo;

    @ManyToOne
    private Comment commentTo;

    private String text;

    @CreationTimestamp
    private LocalDateTime writtenTime;

    private boolean isDeleted;

    public Comment() {
        writtenTime = DateTimeHelper.getNow();
    }

    public Comment(AppUser author, Post postTo, String text) {
        this();
        this.author = author;
        this.postTo = postTo;
        this.text = text;
    }

    public Comment(AppUser author, Post postTo, Comment commentTo, String text) {
        this(author, postTo, text);
        this.commentTo = commentTo;
    }

    public AppUser getAuthor() {
        return author;
    }

    public void setAuthor(AppUser author) {
        this.author = author;
    }

    public Post getPostTo() {
        return postTo;
    }

    public void setPostTo(Post postTo) {
        this.postTo = postTo;
    }

    public Comment getCommentTo() {
        return commentTo;
    }

    public void setCommentTo(Comment commentTo) {
        this.commentTo = commentTo;
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

    public boolean isDeleted() {
        return isDeleted;
    }

    public void deleteComment() {
        isDeleted = true;
    }

}
