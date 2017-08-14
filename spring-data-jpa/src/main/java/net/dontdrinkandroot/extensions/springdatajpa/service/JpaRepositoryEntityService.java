package net.dontdrinkandroot.extensions.springdatajpa.service;

import net.dontdrinkandroot.extensions.springdatajpa.model.Entity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class JpaRepositoryEntityService<T extends Entity<ID>, ID extends Serializable, R extends JpaRepository<T, ID>> implements EntityService<T, ID>
{
    private R repository;

    protected JpaRepositoryEntityService()
    {
        /* Reflection Instantiation */
    }

    public JpaRepositoryEntityService(R repository)
    {
        this.repository = repository;
    }

    protected R getRepository()
    {
        return this.repository;
    }

    @Override
    @Transactional(readOnly = true)
    public T find(ID id)
    {
        return this.getRepository().findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> listAll()
    {
        return this.getRepository().findAll(this.getDefaultSort());
    }

    @Override
    @Transactional(readOnly = true)
    public long findCount()
    {
        return this.getRepository().count();
    }

    @Override
    @Transactional(readOnly = true)
    public Iterator<T> iterate(long first, long count)
    {
        return this.iterate(first, count, this.getDefaultSort());
    }

    @Override
    @Transactional(readOnly = true)
    public Iterator<T> iterate(long first, long count, Sort sort)
    {
        int page = Math.toIntExact(first) / Math.toIntExact(count);
        PageRequest pageRequest = new PageRequest(page, Math.toIntExact(count), sort);
        return this.getRepository().findAll(pageRequest).iterator();
    }

    protected Sort getDefaultSort()
    {
        return null;
    }
}
