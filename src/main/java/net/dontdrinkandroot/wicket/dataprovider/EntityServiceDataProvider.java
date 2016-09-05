package net.dontdrinkandroot.wicket.dataprovider;

import net.dontdrinkandroot.persistence.entity.Entity;
import net.dontdrinkandroot.persistence.service.EntityService;
import net.dontdrinkandroot.wicket.model.EntityLoadableDetachableModel;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;

import java.util.Iterator;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class EntityServiceDataProvider<T extends Entity<K>, K> implements IDataProvider<T>
{
	private final EntityService<T, K> service;
	private Class<T> entityClass;

	public EntityServiceDataProvider(EntityService<T, K> service, Class<T> entityClass)
	{
		this.service = service;
		this.entityClass = entityClass;
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

	public IModel<T> model(T object)
	{
		return new EntityLoadableDetachableModel<T, K>(object, this.entityClass);
	}

	@Override
	public void detach()
	{
		/* Noop */
	}
}
