package net.dontdrinkandroot.wicket.bootstrap.panel;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;

import net.dontdrinkandroot.wicket.behavior.CssClassAppender;
import net.dontdrinkandroot.wicket.bootstrap.css.BootstrapCssClass;
import net.dontdrinkandroot.wicket.component.basic.Heading;
import net.dontdrinkandroot.wicket.component.basic.Heading.Level;


public class ActionPanelHeading extends GenericPanel<Void>
{

	private IModel<String> headingModel;

	private Level headingLevel;


	public ActionPanelHeading(String id, IModel<String> headingModel, Level headingLevel)
	{
		super(id);
		this.headingModel = headingModel;
		this.headingLevel = headingLevel;
	}

	@Override
	protected void onInitialize()
	{
		super.onInitialize();

		Component title = this.createTitle("title");
		this.add(title);

		WebMarkupContainer actions = new WebMarkupContainer("actions");
		this.add(actions);

		RepeatingView actionView = new RepeatingView("action");
		this.populateActionView(actionView);
		actions.add(actionView);
	}

	protected Component createTitle(String id)
	{
		Heading heading = new Heading(id, this.headingModel, this.headingLevel);
		heading.add(new CssClassAppender(BootstrapCssClass.PANEL_TITLE));
		return heading;
	}

	protected void populateActionView(RepeatingView actionView)
	{
		/* Empty hook */
	}

}
