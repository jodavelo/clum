package co.edu.usbcali.clum.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;


/**
 *
 * @author <a href="mailto:dgomez@vortexbird.com">Diego A Gomez</a>
 * @project zathuracode
 * @class JpaGenericRepositoryImpl
 * @date Jun 26, 2018
 *
 */
@SuppressWarnings("unchecked")
@NoRepositoryBean
public class JpaGenericRepositoryImpl<T, ID extends Serializable>
    extends SimpleJpaRepository<T, ID> implements JpaGenericRepository<T, ID>,
        Serializable {
    private Class<T> entityClass;
    private Logger log = null;
    private final EntityManager entityManager;
    private int maxResults = 0;

    public JpaGenericRepositoryImpl(
        JpaEntityInformation<T, ?> entityInformation,
        EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
        this.entityClass = (Class<T>) entityInformation.getJavaType();
        log = LoggerFactory.getLogger(JpaGenericRepositoryImpl.class);
        log.info("" + entityInformation.getClass());
        log.info("" + entityInformation.getJavaType());
    }

    @Override
    public void update(T entity) {
        entityManager.merge(entity);
    }

    @Override
    public List<T> findPage(String sortColumnName, boolean sortAscending,
        int startRow, int maxResults) {
        log.debug("findPage " + entityClass.getName());

        if ((sortColumnName != null) && (sortColumnName.length() > 0)) {
            try {
                String queryString = "select model from " +
                    entityClass.getName() + " model order by model." +
                    sortColumnName + " " + (sortAscending ? "asc" : "desc");

                Query queryObject = entityManager.createQuery(queryString);
                queryObject.setFirstResult(startRow);
                queryObject.setMaxResults(maxResults);

                return queryObject.getResultList();
            } catch (RuntimeException re) {
                throw re;
            }
        } else {
            try {
                String queryString = "select model from " +
                    entityClass.getName() + " model";

                Query queryObject = entityManager.createQuery(queryString);
                queryObject.setFirstResult(startRow);
                queryObject.setMaxResults(maxResults);

                return queryObject.getResultList();
            } catch (RuntimeException re) {
                log.error("findPage " + entityClass.getName() + " failed", re);
                throw re;
            }
        }
    }

    @Override
    public int getMaxResults() {
        return maxResults;
    }

    @Override
    public void setMaxResults(int maxResults) {
        this.maxResults = maxResults;
    }

    @Override
    public void deleteByProperty(String tableName, String propertyName,
        Object value) {
        String queryString = "delete from " + tableName +
            " as model where model." + propertyName + "= ?0";
        Query queryObject = entityManager.createQuery(queryString);
        queryObject.setParameter(0, value);
        queryObject.executeUpdate();
    }

    @Override
    public List<T> findByCriteria(String whereCondition) {
        log.debug("finding " + entityClass.getName() + " " + whereCondition);

        try {
            String where = ((whereCondition == null) ||
                (whereCondition.length() == 0)) ? "" : ("where " +
                whereCondition);
            final String queryString = "select model from " +
                entityClass.getName() + " model " + where;

            Query queryObject = entityManager.createQuery(queryString);

            return queryObject.getResultList();
        } catch (RuntimeException re) {
            log.error("find By Criteria failed", re);
            throw re;
        }
    }

    @Override
    public List<T> findByProperty(String propertyName, Object value) {
        log.debug("finding " + entityClass.getName() +
            " instance with property: " + propertyName + ", value: " + value);

        try {
            String queryString = "select model from " + entityClass.getName() +
                " as model where model." + propertyName + "= ?0";
            Query queryObject = entityManager.createQuery(queryString);
            queryObject.setParameter(0, value);

            return queryObject.getResultList();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    @Override
    public Query createQuery(String queryString) {
        Query query = entityManager.createQuery(queryString);
        prepareQuery(query);

        return query;
    }

    @Override
    public void prepareQuery(Query queryObject) {
        if (getMaxResults() > 0) {
            queryObject.setMaxResults(getMaxResults());
        }
    }
}
