package net.dontdrinkandroot.extensions.wicket.model;

import net.dontdrinkandroot.extensions.springdatajpa.model.Entity;
import net.dontdrinkandroot.extensions.springdatajpa.service.EntityLoader;
import net.dontdrinkandroot.wicket.model.AbstractInjectedLoadableDetachableModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.io.Serializable;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class EntityLoadableDetachableModel<T extends Entity<ID>, ID extends Serializable> extends AbstractInjectedLoadableDetachableModel<T>
{
    private ID id;

    private Class<T> clazz;

    @SpringBean
    private EntityLoader entityLoader;

    public EntityLoadableDetachableModel(IModel<? extends T> model)
    {
        this(model.getObject());
    }

    public EntityLoadableDetachableModel(T object)
    {
        super(object);

        this.id = object.getId();
        this.clazz = (Class<T>) object.getClass();
    }

    public EntityLoadableDetachableModel(Class<T> clazz, ID id)
    {
        this.clazz = clazz;
        this.id = id;
    }

    @Override
    protected T load()
    {
        return this.entityLoader.load(this.clazz, this.id);
    }
}
