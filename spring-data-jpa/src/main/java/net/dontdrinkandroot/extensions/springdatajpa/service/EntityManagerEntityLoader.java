package net.dontdrinkandroot.extensions.springdatajpa.service;

import net.dontdrinkandroot.extensions.springdatajpa.model.Entity;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class EntityManagerEntityLoader implements EntityLoader
{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public <T extends Entity<ID>, ID extends Serializable> T find(Class<T> clazz, ID id)
    {
        return this.entityManager.find(clazz, id);
    }

    @Override
    public <T extends Entity<ID>, ID extends Serializable> T load(Class<T> clazz, ID id)
    {
        T entity = this.find(clazz, id);
        if (null == entity) {
            throw new NoResultException();
        }

        return entity;
    }
}
