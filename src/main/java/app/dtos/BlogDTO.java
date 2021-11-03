package app.dtos;

import app.models.Blog;

public class BlogDTO {

    private String patternName;
    private String blogName;
    private String creatorName;

    public BlogDTO() {}

    public BlogDTO(String patternName, String blogName) {
        this.patternName = patternName;
        this.blogName = blogName;
    }

    public BlogDTO(Blog blog, String creatorName) {
        this(blog.getPattern().getName(), blog.getBlogName());
        this.creatorName = creatorName;
    }

    public BlogDTO(Blog blog) {
        this(blog, blog.getOwner().getUsername());
    }

    public String getPatternName() {
        return patternName;
    }

    public void setPatternName(String patternName) {
        this.patternName = patternName;
    }

    public String getBlogName() {
        return blogName;
    }

    public void setBlogName(String blogName) {
        this.blogName = blogName;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

}
