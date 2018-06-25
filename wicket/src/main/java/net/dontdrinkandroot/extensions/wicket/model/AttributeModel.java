package net.dontdrinkandroot.extensions.wicket.model;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import javax.persistence.metamodel.Attribute;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 *
 * @deprecated In favor of {@link org.apache.wicket.model.LambdaModel}.
 */
@Deprecated
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
