package net.dontdrinkandroot.extensions.wicket.model;

import net.dontdrinkandroot.extensions.springdatajpa.model.Entity;
import net.dontdrinkandroot.extensions.springdatajpa.service.EntityLoader;
import net.dontdrinkandroot.wicket.model.AbstractInjectedLoadableDetachableModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.lang.Args;

import java.io.Serializable;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class EntityLoadableDetachableModel<T extends Entity<ID>, ID extends Serializable> extends AbstractInjectedLoadableDetachableModel<T>
{
    @SpringBean
    private EntityLoader entityLoader;

    private ID id;

    private Class<T> clazz;

    public EntityLoadableDetachableModel(IModel<? extends T> model)
    {
        this(model.getObject());
    }

    public EntityLoadableDetachableModel(T object)
    {
        super(object);

        Args.notNull(object, "Object must not be null");
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
