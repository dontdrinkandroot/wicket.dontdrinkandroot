package net.dontdrinkandroot.extension.wicket.model;

import net.dontdrinkandroot.persistence.entity.Entity;
import net.dontdrinkandroot.persistence.util.EntityLoader;
import net.dontdrinkandroot.wicket.model.AbstractInjectedLoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class EntityLoadableDetachableModel<T extends Entity<K>, K> extends AbstractInjectedLoadableDetachableModel<T>
{
    private final K id;

    private final Class<T> clazz;

    @SpringBean
    private EntityLoader entityLoader;

    public EntityLoadableDetachableModel(T object, Class<T> clazz)
    {
        super(object);

        this.id = object.getId();
        this.clazz = clazz;
    }

    @Override
    protected T load()
    {
        return this.entityLoader.load(this.id, this.clazz);
    }
}
