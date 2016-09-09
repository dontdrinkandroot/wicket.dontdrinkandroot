package net.dontdrinkandroot.extensions.wicket.bootstrap.component.progress.table;

import net.dontdrinkandroot.wicket.behavior.CssClassAppender;
import net.dontdrinkandroot.wicket.bootstrap.css.BootstrapCssClass;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public abstract class RepeatingButtonPanel<T> extends GenericPanel<T>
{
    public RepeatingButtonPanel(String id, IModel<T> model)
    {
        super(id, model);
    }

    @Override
    protected void onInitialize()
    {
        super.onInitialize();

        this.add(new CssClassAppender(BootstrapCssClass.BTN_GROUP));

        RepeatingView buttonView = new RepeatingView("button");
        this.populateButtons(buttonView);
        this.add(buttonView);
    }

    protected abstract void populateButtons(RepeatingView buttonView);
}
