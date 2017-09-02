package net.dontdrinkandroot.extensions.springdatajpa.util;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * Utility methods for an {@link EntityManager}.
 *
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class EntityManagerHelper
{
    private EntityManager entityManager;

    public EntityManagerHelper(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    public <T> List<T> find(CriteriaQuery<T> query)
    {
        TypedQuery<T> typedQuery = this.getEntityManager().createQuery(query);
        return typedQuery.getResultList();
    }

    public <T> T findSingle(CriteriaQuery<T> query)
    {
        TypedQuery<T> typedQuery = this.getEntityManager().createQuery(query);
        return typedQuery.getSingleResult();
    }

    public <T> T findSingleOrNull(CriteriaQuery<T> query)
    {
        try {
            return this.findSingle(query);
        } catch (NoResultException e) {
            return null;
        }
    }

    public EntityManager getEntityManager()
    {
        return this.entityManager;
    }

    public CriteriaBuilder getCriteriaBuilder()
    {
        return this.entityManager.getCriteriaBuilder();
    }
}
