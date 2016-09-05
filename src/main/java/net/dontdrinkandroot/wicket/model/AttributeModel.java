package net.dontdrinkandroot.wicket.model;

import org.apache.wicket.model.PropertyModel;

import javax.persistence.metamodel.Attribute;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class AttributeModel<E, T> extends PropertyModel<T>
{
	public AttributeModel(Object modelObject, Attribute<E, T> attribute)
	{
		super(modelObject, attribute.getName());
	}
}
