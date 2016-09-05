package net.dontdrinkandroot.wicket.dataprovider;

import net.dontdrinkandroot.persistence.entity.Entity;
import net.dontdrinkandroot.persistence.service.EntityService;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortState;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import javax.persistence.metamodel.SingularAttribute;
import java.util.Iterator;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class SortableEntityServiceDataProvider<T extends Entity<K>, K> extends SortableDataProvider<T, SingularAttribute<T, ?>>
{
	private final EntityService<T, K> service;

	public SortableEntityServiceDataProvider(EntityService<T, K> service)
	{
		this.service = service;
	}

	@Override
	public Iterator<? extends T> iterator(long first, long count)
	{
		SortParam<SingularAttribute<T, ?>> sort = this.getSort();
		if (null != sort) {
			SingularAttribute<T, ?> sortAttribute = sort.getProperty();
			ISortState<SingularAttribute<T, ?>> sortState = this.getSortState();
			return this.service.listAll(first, count, sortAttribute, sort.isAscending()).iterator();
		}

		return this.service.listAll(first, count).iterator();
	}

	@Override
	public long size()
	{
		return this.service.findCount();
	}

	@Override
	public IModel<T> model(T object)
	{
		return new LoadableDetachableModel<T>(object)
		{
			private K id = object.getId();

			@Override
			protected T load()
			{
				return SortableEntityServiceDataProvider.this.service.find(this.id);
			}
		};
	}

	@Override
	public void detach()
	{
		/* Noop */
	}
}
