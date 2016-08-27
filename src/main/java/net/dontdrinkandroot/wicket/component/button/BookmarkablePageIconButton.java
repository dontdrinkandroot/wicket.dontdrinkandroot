package net.dontdrinkandroot.wicket.component.button;

import org.apache.wicket.Page;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import net.dontdrinkandroot.wicket.behavior.CssClassAppender;
import net.dontdrinkandroot.wicket.behavior.TitleModifier;
import net.dontdrinkandroot.wicket.bootstrap.behavior.IconBehavior;
import net.dontdrinkandroot.wicket.bootstrap.component.button.BookmarkablePageButton;
import net.dontdrinkandroot.wicket.css.CssClass;
import net.dontdrinkandroot.wicket.css.DontdrinkandrootCssClass;


public class BookmarkablePageIconButton<T> extends BookmarkablePageButton<T>
{

	private IModel<CssClass> iconClassModel;

	private IModel<String> titleModel;


	public <C extends Page> BookmarkablePageIconButton(
			String id,
			Class<C> pageClass,
			IModel<CssClass> iconClassModel,
			IModel<String> titleModel)
	{
		super(id, pageClass);

		this.iconClassModel = iconClassModel;
		this.titleModel = titleModel;
	}

	public <C extends Page> BookmarkablePageIconButton(
			String id,
			Class<C> pageClass,
			PageParameters parameters,
			IModel<CssClass> iconClassModel,
			IModel<String> titleModel)
	{
		super(id, pageClass, parameters);

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