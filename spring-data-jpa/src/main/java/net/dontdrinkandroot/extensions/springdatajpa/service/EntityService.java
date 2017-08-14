package net.dontdrinkandroot.extensions.springdatajpa.service;

import net.dontdrinkandroot.extensions.springdatajpa.model.Entity;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public interface EntityService<T extends Entity<ID>, ID extends Serializable>
{
    T find(ID id);

    List<T> listAll();

    long findCount();

    Iterator<T> iterate(long first, long count);

    Iterator<T> iterate(long first, long count, Sort sort);
}
