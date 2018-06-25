package net.dontdrinkandroot.extensions.springdatajpa.service;

import net.dontdrinkandroot.extensions.springdatajpa.model.Entity;
import net.dontdrinkandroot.extensions.springdatajpa.repository.GenericSpecifications;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.metamodel.SingularAttribute;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
@Transactional(readOnly = true)
public class JpaRepositoryEntityService<T extends Entity<ID>, ID extends Serializable, R extends JpaRepository<T, ID> & JpaSpecificationExecutor<T>> implements EntityService<T, ID>
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
    @Transactional
    public T save(T entity)
    {
        return this.getRepository().save(entity);
    }

    @Override
    public Optional<T> find(ID id)
    {
        return this.getRepository().findById(id);
    }

    @Override
    public List<T> listAll()
    {
        return this.getRepository().findAll(this.getDefaultSort());
    }

    @Override
    public long findCount()
    {
        return this.getRepository().count();
    }

    @Override
    public Iterator<T> iterate(long first, long count)
    {
        return this.iterate(first, count, this.getDefaultSort());
    }

    @Override
    public Iterator<T> iterate(long first, long count, Sort sort)
    {
        PageRequest pageRequest = this.getPageRequest(first, count, sort);
        return this.getRepository().findAll(pageRequest).iterator();
    }

    @Override
    public <V> long findCountBy(SingularAttribute<T, V> attribute, V value)
    {
        return this.getRepository().count(GenericSpecifications.byAttribute(attribute, value));
    }

    @Override
    public <V> Iterator<T> iterateBy(SingularAttribute<T, V> attribute, V value, long first, long count)
    {
        return this.iterateBy(attribute, value, first, count, this.getDefaultSort());
    }

    @Override
    public <V> Iterator<T> iterateBy(SingularAttribute<T, V> attribute, V value, long first, long count, Sort sort)
    {
        PageRequest pageRequest = this.getPageRequest(first, count, sort);
        return this.getRepository()
                .findAll(GenericSpecifications.byAttribute(attribute, value), pageRequest)
                .iterator();
    }

    @Override
    @Transactional
    public void delete(T entity)
    {
        this.getRepository().delete(entity);
    }

    protected Sort getDefaultSort()
    {
        return Sort.unsorted();
    }

    private PageRequest getPageRequest(long first, long count, Sort sort)
    {
        int page = Math.toIntExact(first) / Math.toIntExact(count);
        return new PageRequest(page, Math.toIntExact(count), sort);
    }
}
