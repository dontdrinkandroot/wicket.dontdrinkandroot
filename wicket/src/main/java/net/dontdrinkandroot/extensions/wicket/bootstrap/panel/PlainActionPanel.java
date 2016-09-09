package net.dontdrinkandroot.extensions.wicket.bootstrap.panel;

import net.dontdrinkandroot.wicket.bootstrap.component.panel.PlainPanel;
import net.dontdrinkandroot.wicket.component.basic.Heading;
import org.apache.wicket.Component;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;

public class PlainActionPanel<T> extends PlainPanel<T>
{
    protected IModel<String> headingModel;

    protected Heading.Level headingLevel;

    public PlainActionPanel(String id)
    {
        super(id);
    }

    public PlainActionPanel(String id, IModel<T> model)
    {
        super(id, model);
    }

    public PlainActionPanel(String id, IModel<String> headingModel, Heading.Level headingLevel)
    {
        super(id);
        this.headingModel = headingModel;
        this.headingLevel = headingLevel;
    }

    public PlainActionPanel(String id, IModel<T> model, IModel<String> headingModel, Heading.Level headingLevel)
    {
        super(id, model);
        this.headingModel = headingModel;
        this.headingLevel = headingLevel;
    }

    @Override
    protected Component createHeading(String id)
    {
        return new ActionPanelHeading(id, this.getHeadingModel(), this.getHeadingLevel())
        {

            @Override
            protected void populateActionView(RepeatingView actionView)
            {
                PlainActionPanel.this.populateActionView(actionView);
            }
        };
    }

    protected void populateActionView(RepeatingView actionView)
    {
        /* Empty hook */
    }

    public IModel<String> getHeadingModel()
    {
        return this.headingModel;
    }

    public Heading.Level getHeadingLevel()
    {
        return this.headingLevel;
    }
}
