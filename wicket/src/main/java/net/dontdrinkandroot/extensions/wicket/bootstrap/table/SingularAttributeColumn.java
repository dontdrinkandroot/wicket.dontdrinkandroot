package net.dontdrinkandroot.extensions.wicket.bootstrap.table;

import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.export.IExportableColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import javax.persistence.metamodel.SingularAttribute;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class SingularAttributeColumn<T> extends AbstractColumn<T, SingularAttribute<T, ?>> implements IExportableColumn<T, SingularAttribute<T, ?>>
{
    public SingularAttributeColumn(IModel<String> displayModel, SingularAttribute<T, ?> sortProperty)
    {
        super(displayModel, sortProperty);
    }

    @Override
    public void populateItem(
            final Item<ICellPopulator<T>> item, final String componentId,
            final IModel<T> rowModel
    )
    {
        item.add(new Label(componentId, this.getDataModel(rowModel)));
    }

    @Override
    public IModel<?> getDataModel(IModel<T> rowModel)
    {
        return new PropertyModel<>(rowModel, this.getSortProperty().getName());
    }
}
