package net.dontdrinkandroot.extensions.wicket.model;

import net.dontdrinkandroot.metagen.model.Attribute;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class MetagenModel<E, T> extends PropertyModel<T>
{
    public MetagenModel(E modelObject, Attribute<E, T> attribute)
    {
        super(modelObject, attribute.getName());
    }

    public MetagenModel(IModel<? extends E> model, Attribute<E, T> attribute)
    {
        super(model, attribute.getName());
    }
}
