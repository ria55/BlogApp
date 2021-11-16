package app.services;

import app.dtos.BlogDTO;
import app.models.AppUser;
import app.models.Blog;
import app.models.BlogPattern;
import app.returnModels.Feedback;
import app.returnModels.ObjectBack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class BlogService extends ServiceBase {

    private UserService userService;

    @Autowired
    public BlogService(UserService userService) {
        this.userService = userService;
    }

    @Transactional
    public Feedback createPattern(BlogPattern pattern) {
        boolean success = addPattern(pattern);

        if (success) {
            BlogPattern added = findPattern(pattern.getName());

            if (added != null) {
                return new ObjectBack<>(added);
            }
        }

        return new Feedback(false, HttpStatus.BAD_GATEWAY);
    }

    @Transactional
    protected boolean addPattern(BlogPattern pattern) {
        try {
            em.persist(pattern);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public BlogPattern findPattern(String patternName) {
        return em.createQuery("SELECT p FROM BlogPattern p WHERE p.name = :name", BlogPattern.class)
                .setParameter("name", patternName)
                .getSingleResult();
    }

    @Transactional
    public List<BlogPattern> findAllPatterns() {
        return em.createQuery("SELECT p FROM BlogPattern p", BlogPattern.class)
                .getResultList();
    }

    @Transactional
    public BlogDTO findBlogById(long id) {
        try {
            Blog blog = em.createQuery("SELECT b FROM Blog b WHERE b.id = :id", Blog.class)
                    .setParameter("id", id)
                    .getSingleResult();

            return new BlogDTO(blog);
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public Blog findBlogByNameAndUsername(String blogName, String username) {
        try {
            return em.createQuery("SELECT b FROM Blog b WHERE b.blogName = :blogName AND b.owner.username = :username", Blog.class)
                    .setParameter("blogName", blogName)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public List<BlogDTO> listAllBlogs() {
        try {
            List<Blog> blogs = em.createQuery("SELECT b FROM Blog b", Blog.class)
                    .getResultList();

            List<BlogDTO> blogDTO = new ArrayList<>();

            for (Blog blog : blogs) {
                blogDTO.add(new BlogDTO(blog));
            }

            return blogDTO;
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public Feedback createBlog(BlogDTO blogDTO) {
        boolean success = addNewBlog(blogDTO);

        if (success) {
            Blog blog = findBlogByNameAndUsername(blogDTO.getBlogName(), blogDTO.getCreatorName());

            if (blog != null) {
                return new ObjectBack<>(new BlogDTO(blog));
            }
        }

        return new Feedback(false, HttpStatus.BAD_REQUEST, "no blog created");
    }

    @Transactional
    protected boolean addNewBlog(BlogDTO blogDTO) {
        try {
            AppUser user = userService.getLoggedInUser();
            BlogPattern pattern = findPattern(blogDTO.getPatternName());

            Blog blog = new Blog(user, blogDTO.getBlogName(), pattern);
            em.persist(blog);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public void addBlog(Blog blog) {
        em.persist(blog);
    }

}
