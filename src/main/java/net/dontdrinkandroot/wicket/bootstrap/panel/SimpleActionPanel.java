package net.dontdrinkandroot.wicket.bootstrap.panel;

import org.apache.wicket.Component;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;

import net.dontdrinkandroot.wicket.bootstrap.component.panel.SimplePanel;
import net.dontdrinkandroot.wicket.component.basic.Heading.Level;


public class SimpleActionPanel<T> extends SimplePanel<T>
{

	public SimpleActionPanel(String id, IModel<T> model)
	{
		super(id, model);
	}

	public SimpleActionPanel(String id, IModel<T> model, IModel<String> headingModel)
	{
		super(id, model, headingModel);
	}

	public SimpleActionPanel(String id, IModel<String> headingModel, Level headingLevel)
	{
		super(id, headingModel, headingLevel);
	}

	public SimpleActionPanel(String id, IModel<T> model, IModel<String> headingModel, Level headingLevel)
	{
		super(id, model, headingModel, headingLevel);
	}

	@Override
	protected Component createHeading(String id)
	{
		return new ActionPanelHeading(id, this.getHeadingModel(), this.getHeadingLevel()) {

			@Override
			protected void populateActionView(RepeatingView actionView)
			{
				SimpleActionPanel.this.populateActionView(actionView);
			}
		};
	}

	protected void populateActionView(RepeatingView actionView)
	{
		/* Empty hook */
	}

}
