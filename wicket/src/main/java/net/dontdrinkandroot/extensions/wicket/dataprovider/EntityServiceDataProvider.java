package net.dontdrinkandroot.extensions.wicket.dataprovider;

import net.dontdrinkandroot.extensions.springdatajpa.model.Entity;
import net.dontdrinkandroot.extensions.springdatajpa.service.EntityService;
import net.dontdrinkandroot.extensions.wicket.model.EntityLoadableDetachableModel;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;

import java.io.Serializable;
import java.util.Iterator;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class EntityServiceDataProvider<T extends Entity<ID>, ID extends Serializable> implements IDataProvider<T>
{
    private final EntityService<T, ID> service;

    private Class<T> entityClass;

    public EntityServiceDataProvider(EntityService<T, ID> service, Class<T> entityClass)
    {
        this.service = service;
        this.entityClass = entityClass;
    }

    @Override
    public Iterator<? extends T> iterator(long first, long count)
    {
        return this.service.iterate(first, count);
    }

    @Override
    public long size()
    {
        return this.service.findCount();
    }

    public IModel<T> model(T object)
    {
        return new EntityLoadableDetachableModel<>(object);
    }

    @Override
    public void detach()
    {
        /* Noop */
    }
}
