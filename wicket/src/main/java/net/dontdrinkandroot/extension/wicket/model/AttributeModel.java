package net.dontdrinkandroot.extension.wicket.model;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import javax.persistence.metamodel.Attribute;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class AttributeModel<E, T> extends PropertyModel<T>
{
    public AttributeModel(E modelObject, Attribute<E, T> attribute)
    {
        super(modelObject, attribute.getName());
    }

    public AttributeModel(IModel<? extends E> model, Attribute<E, T> attribute)
    {
        super(model, attribute.getName());
    }
}
