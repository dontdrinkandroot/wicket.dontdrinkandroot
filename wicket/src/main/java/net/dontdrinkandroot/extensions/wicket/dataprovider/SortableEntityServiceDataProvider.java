package net.dontdrinkandroot.extensions.wicket.dataprovider;

import net.dontdrinkandroot.extensions.springdatajpa.model.Entity;
import net.dontdrinkandroot.extensions.springdatajpa.service.EntityService;
import net.dontdrinkandroot.extensions.wicket.model.EntityLoadableDetachableModel;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;

import javax.persistence.metamodel.SingularAttribute;
import java.io.Serializable;
import java.util.Iterator;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class SortableEntityServiceDataProvider<T extends Entity<ID>, ID extends Serializable> extends SortableDataProvider<T, SingularAttribute<T, ?>>
{
    private final EntityService<T, ID> service;

    private final Class<T> entityClass;

    public SortableEntityServiceDataProvider(EntityService<T, ID> service, Class<T> clazz)
    {
        this.service = service;
        this.entityClass = clazz;
    }

    @Override
    public Iterator<? extends T> iterator(long first, long count)
    {
        SortParam<SingularAttribute<T, ?>> sort = this.getSort();
        if (null != sort) {
            SingularAttribute<T, ?> sortAttribute = sort.getProperty();
            return this.service.iterate(
                    first,
                    count,
                    new JpaSort(sort.isAscending() ? Sort.Direction.ASC : Sort.Direction.DESC, sortAttribute)
            );
        }

        return this.service.iterate(first, count);
    }

    @Override
    public long size()
    {
        return this.service.findCount();
    }

    @Override
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
