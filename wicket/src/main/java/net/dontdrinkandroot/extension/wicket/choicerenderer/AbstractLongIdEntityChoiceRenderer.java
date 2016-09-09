package net.dontdrinkandroot.extension.wicket.choicerenderer;

import net.dontdrinkandroot.persistence.entity.Entity;
import net.dontdrinkandroot.utils.lang.StringUtils;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;

import java.util.List;

public abstract class AbstractLongIdEntityChoiceRenderer<T extends Entity<Long>> implements IChoiceRenderer<T>
{
	@Override
	public String getIdValue(T object, int index)
	{
		if (null == object) {
			return "null";
		}

		return object.getId().toString();
	}

	@Override
	public T getObject(String id, IModel<? extends List<? extends T>> choicesModel)
	{
		if (StringUtils.isBlank(id)) {
			return null;
		}

		Long longId = Long.parseLong(id);
		List<? extends T> choices = choicesModel.getObject();
		for (T choice : choices) {
			if (choice.getId().equals(longId)) {
				return choice;
			}
		}

		return null;
	}
}
