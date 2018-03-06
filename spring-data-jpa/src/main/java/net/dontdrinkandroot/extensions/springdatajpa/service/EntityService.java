package net.dontdrinkandroot.extensions.springdatajpa.service;

import net.dontdrinkandroot.extensions.springdatajpa.model.Entity;
import org.springframework.data.domain.Sort;

import javax.persistence.metamodel.SingularAttribute;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public interface EntityService<T extends Entity<ID>, ID extends Serializable>
{
    T save(T entity);

    T find(ID id);

    void delete(T entity);

    List<T> listAll();

    long findCount();

    Iterator<T> iterate(long first, long count);

    Iterator<T> iterate(long first, long count, Sort sort);

    <V> long findCountBy(SingularAttribute<T, V> customer, V value);

    <V> Iterator<T> iterateBy(SingularAttribute<T, V> customer, V value, long first, long count);

    <V> Iterator<T> iterateBy(SingularAttribute<T, V> customer, V value, long first, long count, Sort sort);
}
