package net.dontdrinkandroot.wicket.bootstrap.panel;

import org.apache.wicket.Component;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;

import net.dontdrinkandroot.wicket.bootstrap.component.panel.PlainPanel;
import net.dontdrinkandroot.wicket.component.basic.Heading.Level;


public class ActionPanel<T> extends PlainPanel<T>
{

	private IModel<String> headingModel;

	private Level headingLevel;


	public ActionPanel(String id, IModel<String> headingModel, Level headingLevel)
	{
		super(id);
		this.headingModel = headingModel;
		this.headingLevel = headingLevel;
	}

	public ActionPanel(String id, IModel<T> model, IModel<String> headingModel, Level headingLevel)
	{
		super(id, model);
		this.headingModel = headingModel;
		this.headingLevel = headingLevel;
	}

	@Override
	protected Component createHeading(String id)
	{
		return new ActionPanelHeading(id, this.headingModel, this.headingLevel) {

			@Override
			protected void populateActionView(RepeatingView actionView)
			{
				ActionPanel.this.populateActionView(actionView);
			}
		};
	}

	protected void populateActionView(RepeatingView actionView)
	{
		/* Empty hook */
	}

}
