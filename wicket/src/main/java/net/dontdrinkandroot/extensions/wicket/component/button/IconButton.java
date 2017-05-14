package net.dontdrinkandroot.extensions.wicket.component.button;

import net.dontdrinkandroot.extensions.wicket.css.DontdrinkandrootCssClass;
import net.dontdrinkandroot.wicket.behavior.CssClassAppender;
import net.dontdrinkandroot.wicket.behavior.TitleModifier;
import net.dontdrinkandroot.wicket.bootstrap.behavior.IconBehavior;
import net.dontdrinkandroot.wicket.bootstrap.component.button.Button;
import net.dontdrinkandroot.wicket.css.CssClass;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public abstract class IconButton<T> extends Button<T>
{
    private IModel<CssClass> iconClassModel;

    private IModel<String> titleModel;

    public IconButton(String id, CssClass iconClass, IModel<String> titleModel)
    {
        super(id);

        this.iconClassModel = Model.of(iconClass);
        this.titleModel = titleModel;
    }

    public IconButton(String id, IModel<T> model, CssClass iconClass, IModel<String> titleModel)
    {
        super(id, model);

        this.iconClassModel = Model.of(iconClass);
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
