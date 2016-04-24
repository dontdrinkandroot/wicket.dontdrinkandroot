package net.dontdrinkandroot.wicket.bootstrap.panel;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;

import net.dontdrinkandroot.wicket.behavior.CssClassAppender;
import net.dontdrinkandroot.wicket.bootstrap.behavior.ButtonBehavior;
import net.dontdrinkandroot.wicket.bootstrap.behavior.IconBehavior;
import net.dontdrinkandroot.wicket.bootstrap.component.panel.Panel;
import net.dontdrinkandroot.wicket.bootstrap.css.BootstrapCssClass;
import net.dontdrinkandroot.wicket.bootstrap.css.FontAwesomeIcon;
import net.dontdrinkandroot.wicket.bootstrap.css.FontAwesomeIconClass;
import net.dontdrinkandroot.wicket.component.basic.Heading.Level;


abstract public class EditableActionPanel<T> extends PlainActionPanel<T>
{

	protected AjaxLink<Void> editLink;

	protected boolean inEditMode = false;


	public EditableActionPanel(String id, IModel<String> headingModel, Level headingLevel)
	{
		super(id, headingModel, headingLevel);
	}

	public EditableActionPanel(String id, IModel<T> model, IModel<String> headingModel, Level headingLevel)
	{
		super(id, model, headingModel, headingLevel);
	}

	public EditableActionPanel(String id, IModel<T> model)
	{
		super(id, model);
	}

	@Override
	protected void populateActionView(RepeatingView actionView)
	{
		this.editLink = new AjaxLink<Void>(actionView.newChildId()) {

			@Override
			protected void onConfigure()
			{
				super.onConfigure();
				this.setVisible(EditableActionPanel.this.editLinkVisible());
				this.setEnabled(EditableActionPanel.this.editLinkEnabled());
			}

			@Override
			public void onClick(AjaxRequestTarget target)
			{
				EditableActionPanel.this.toggleEditMode(target);
			}

		};
		this.editLink.add(new ButtonBehavior());
		this.editLink.add(new IconBehavior(new FontAwesomeIcon(FontAwesomeIconClass.PENCIL).setFixedWidth(true)));
		actionView.add(this.editLink);
	}

	protected void toggleEditMode(AjaxRequestTarget target)
	{
		if (this.inEditMode) {
			this.body = this.createBody(Panel.BODY_ID);
		} else {
			this.body = this.createEditComponent(Panel.BODY_ID);
		}
		this.body.add(new CssClassAppender(BootstrapCssClass.PANEL_BODY));
		this.inEditMode = !this.inEditMode;
		target.add(this.body);
		this.replace(this.body);
	}

	protected boolean editLinkEnabled()
	{
		return true;
	}

	protected boolean editLinkVisible()
	{
		return true;
	}

	abstract protected Component createEditComponent(String id);

}
