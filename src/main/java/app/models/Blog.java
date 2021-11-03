package app.models;

import app.dtos.BlogDTO;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Blog {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private AppUser owner;

    private String blogName;

    @ManyToOne
    private BlogPattern pattern;

    @CreationTimestamp
    private LocalDateTime creationTime;
    private boolean isHidden;

    @OneToMany(mappedBy = "releasedBlog")
    private List<Post> posts;

    public Blog() {
        posts = new ArrayList<>();
    }

    public Blog(AppUser owner, String blogName, BlogPattern pattern) {
        this();
        this.owner = owner;
        this.blogName = blogName;
        this.pattern = pattern;
    }

    public Blog(AppUser owner, String blogName, BlogPattern pattern, boolean isHidden) {
        this(owner, blogName, pattern);
        this.isHidden = isHidden;
    }

    public AppUser getOwner() {
        return owner;
    }

    public void setOwner(AppUser owner) {
        this.owner = owner;
    }

    public String getBlogName() {
        return blogName;
    }

    public void setBlogName(String blogName) {
        this.blogName = blogName;
    }

    public BlogPattern getPattern() {
        return pattern;
    }

    public void setPattern(BlogPattern pattern) {
        this.pattern = pattern;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public void hideBlog() {
        setHidden(true);
    }

    public void unhideBlog() {
        setHidden(false);
    }

    private void setHidden(boolean hidden) {
        if (isHidden != hidden) {
            isHidden = hidden;
        }
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

}
