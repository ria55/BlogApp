package app.services;

import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Service
public class ServiceBase {

    @PersistenceContext
    protected EntityManager em;

    public <T> Long count(Class<T> aClass) {
        try {
            return countRecords(aClass);
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    protected <T> long countRecords(Class<T> aClass) {
        return em.createQuery(getCountQuery(aClass), Long.class).getSingleResult();
    }

    private <T> String getCountQuery(Class<T> aClass) {
        String tableName = aClass.getSimpleName();
        return "SELECT COUNT(*) FROM " + tableName;
    }

}
