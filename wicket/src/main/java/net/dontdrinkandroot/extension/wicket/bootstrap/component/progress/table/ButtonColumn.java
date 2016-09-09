package net.dontdrinkandroot.extension.wicket.bootstrap.component.progress.table;

import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public abstract class ButtonColumn<T, S> extends AbstractColumn<T, S>
{
    public ButtonColumn(IModel<String> displayModel)
    {
        super(displayModel);
    }

    @Override
    public void populateItem(Item<ICellPopulator<T>> cellItem, String componentId, IModel<T> rowModel)
    {
        cellItem.add(new RepeatingButtonPanel<T>(componentId, rowModel)
        {
            @Override
            protected void populateButtons(RepeatingView buttonView)
            {
                ButtonColumn.this.populateButtons(buttonView, rowModel);
            }
        });
    }

    protected abstract void populateButtons(RepeatingView buttonView, IModel<T> model);
}
