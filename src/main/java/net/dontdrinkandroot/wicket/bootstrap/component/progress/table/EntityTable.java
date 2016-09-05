package net.dontdrinkandroot.wicket.bootstrap.component.progress.table;

import net.dontdrinkandroot.wicket.behavior.CssClassAppender;
import net.dontdrinkandroot.wicket.bootstrap.css.BootstrapCssClass;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.repeater.data.sort.AjaxFallbackOrderByBorder;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortStateLocator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.HeadersToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.request.resource.PackageResourceReference;

import javax.persistence.metamodel.SingularAttribute;
import java.util.List;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class EntityTable<T> extends DataTable<T, SingularAttribute<T, ?>>
{

	public EntityTable(
			String id,
			List<? extends IColumn<T, SingularAttribute<T, ?>>> iColumns,
			ISortableDataProvider<T, SingularAttribute<T, ?>> dataProvider
	)
	{
		super(id, iColumns, dataProvider, 10);
		this.setOutputMarkupId(true);
		this.addTopToolbar(new HeadersToolbar<SingularAttribute<T, ?>>(this, dataProvider)
		{
			@Override
			protected WebMarkupContainer newSortableHeader(
					String headerId,
					SingularAttribute<T, ?> property,
					ISortStateLocator<SingularAttribute<T, ?>> locator
			)
			{
				return new AjaxFallbackOrderByBorder<SingularAttribute<T, ?>>(headerId, property, locator)
				{
					@Override
					protected void onSortChanged()
					{
						EntityTable.this.setCurrentPage(0);
					}

					@Override
					protected void onAjaxClick(AjaxRequestTarget target)
					{
						target.add(EntityTable.this);
					}
				};
			}
		});
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
