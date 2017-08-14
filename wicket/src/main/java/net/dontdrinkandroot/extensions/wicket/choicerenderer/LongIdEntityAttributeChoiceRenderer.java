package net.dontdrinkandroot.extensions.wicket.choicerenderer;

import net.dontdrinkandroot.extensions.springdatajpa.model.Entity;
import org.apache.wicket.core.util.lang.PropertyResolver;

import javax.persistence.metamodel.Attribute;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class LongIdEntityAttributeChoiceRenderer<T extends Entity<Long>> extends AbstractLongIdEntityChoiceRenderer<T>
{
    private final Attribute<T, ?> attribute;

    public LongIdEntityAttributeChoiceRenderer(Attribute<T, ?> attribute)
    {
        this.attribute = attribute;
    }

    @Override
    public Object getDisplayValue(T object)
    {
        return PropertyResolver.getValue(this.attribute.getName(), object);
    }
}
