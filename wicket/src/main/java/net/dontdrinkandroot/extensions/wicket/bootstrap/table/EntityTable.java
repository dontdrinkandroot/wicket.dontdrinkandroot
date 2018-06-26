package net.dontdrinkandroot.extensions.wicket.bootstrap.table;

import net.dontdrinkandroot.wicket.behavior.CssClassAppender;
import net.dontdrinkandroot.wicket.bootstrap.css.BootstrapCssClass;
import org.apache.wicket.extensions.ajax.markup.html.repeater.data.table.AjaxFallbackHeadersToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.request.resource.PackageResourceReference;

import javax.persistence.metamodel.SingularAttribute;
import java.util.List;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class EntityTable<T> extends DataTable<T, SingularAttribute<? super T, ?>>
{
    public EntityTable(
            String id,
            List<? extends IColumn<T, SingularAttribute<? super T, ?>>> columns,
            ISortableDataProvider<T, SingularAttribute<? super T, ?>> dataProvider
    )
    {
        super(id, columns, dataProvider, 10);

        this.setOutputMarkupId(true);
        this.addTopToolbar(new AjaxFallbackHeadersToolbar<>(this, dataProvider));
    }

    @Override
    protected void onInitialize()
    {
        super.onInitialize();
        this.add(new CssClassAppender(BootstrapCssClass.TABLE));
    }

    @Override
    protected void onComponentTag(ComponentTag tag)
    {
        tag.setName("table");
        super.onComponentTag(tag);
    }

    @Override
    public void renderHead(IHeaderResponse response)
    {
        super.renderHead(response);
        response.render(CssHeaderItem.forReference(
                new PackageResourceReference(this.getClass(), "style.css"),
                null,
                null,
                null
        ));
    }
}
