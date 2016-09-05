package net.dontdrinkandroot.wicket.dataprovider;

import net.dontdrinkandroot.persistence.entity.Entity;
import net.dontdrinkandroot.persistence.service.EntityService;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import java.util.Iterator;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class EntityServiceDataProvider<T extends Entity<K>, K> implements IDataProvider<T>
{
	private final EntityService<T, K> service;

	public EntityServiceDataProvider(EntityService<T, K> service)
	{
		this.service = service;
	}

	@Override
	public Iterator<? extends T> iterator(long first, long count)
	{
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
				return EntityServiceDataProvider.this.service.find(this.id);
			}
		};
	}

	@Override
	public void detach()
	{
		/* Noop */
	}
}
