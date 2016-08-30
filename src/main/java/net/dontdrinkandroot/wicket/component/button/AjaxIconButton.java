package net.dontdrinkandroot.wicket.component.button;

import net.dontdrinkandroot.wicket.behavior.CssClassAppender;
import net.dontdrinkandroot.wicket.behavior.TitleModifier;
import net.dontdrinkandroot.wicket.bootstrap.behavior.IconBehavior;
import net.dontdrinkandroot.wicket.bootstrap.component.button.AjaxButton;
import net.dontdrinkandroot.wicket.css.CssClass;
import net.dontdrinkandroot.wicket.css.DontdrinkandrootCssClass;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public abstract class AjaxIconButton<T> extends AjaxButton<T>
{

	private IModel<CssClass> iconClassModel;

	private IModel<String> titleModel;


	public AjaxIconButton(String id, IModel<T> model)
	{
		super(id, model);
	}

	public AjaxIconButton(String id, CssClass iconClass, IModel<String> titleModel)
	{
		super(id);

		this.iconClassModel = Model.of(iconClass);
		this.titleModel = titleModel;
	}

	public AjaxIconButton(String id, IModel<CssClass> iconClassModel, IModel<String> titleModel)
	{
		super(id);

		this.iconClassModel = iconClassModel;
		this.titleModel = titleModel;
	}

	public AjaxIconButton(String id, IModel<T> model, CssClass iconClass, IModel<String> titleModel)
	{
		super(id, model);

		this.iconClassModel = Model.of(iconClass);
		this.titleModel = titleModel;
	}

	public AjaxIconButton(String id, IModel<T> model, IModel<CssClass> iconClassModel, IModel<String> titleModel)
	{
		super(id, model);

		this.iconClassModel = iconClassModel;
		this.titleModel = titleModel;
	}

	@Override
	protected void onInitialize()
	{
		super.onInitialize();

		this.add(new CssClassAppender(DontdrinkandrootCssClass.BTN_ICON));
		this.add(new IconBehavior(this.getIconClassModel()));
		this.add(new TitleModifier(this.getTitleModel()));
	}

	public IModel<CssClass> getIconClassModel()
	{
		return this.iconClassModel;
	}

	public IModel<String> getTitleModel()
	{
		return this.titleModel;
	}
}
