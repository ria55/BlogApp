package app.services;

import app.models.BlogPattern;
import app.returnModels.Feedback;
import app.returnModels.ObjectBack;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BlogService extends ServiceBase {

    public Feedback addPattern(BlogPattern pattern) {
        savePattern(pattern);

        try {
            return new ObjectBack<>(findPattern(pattern.getName()));
        } catch (Exception e) {
            return new Feedback(false, HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Transactional
    void savePattern(BlogPattern pattern) {
        em.persist(pattern);
    }

    @Transactional
    public BlogPattern findPattern(String patternName) {
        return em.createQuery("SELECT p FROM BlogPattern p WHERE p.name = :name", BlogPattern.class)
                .setParameter("name", patternName)
                .getSingleResult();
    }

}
