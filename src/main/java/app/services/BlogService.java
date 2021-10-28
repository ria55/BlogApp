package app.services;

import app.models.BlogPattern;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BlogService extends ServiceBase {

    public BlogPattern addPattern(BlogPattern pattern) {
        savePattern(pattern);

        try {
            return findPattern(pattern.getName());
        } catch (Exception e) {
            return null;
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
