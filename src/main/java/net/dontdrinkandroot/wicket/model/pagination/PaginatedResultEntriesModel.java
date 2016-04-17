package net.dontdrinkandroot.wicket.model.pagination;

import java.util.List;

import org.apache.wicket.model.IModel;

import net.dontdrinkandroot.utils.pagination.PaginatedResult;
import net.dontdrinkandroot.wicket.model.AbstractChainedModel;


public class PaginatedResultEntriesModel<T> extends AbstractChainedModel<PaginatedResult<T>, List<T>>
{

	public PaginatedResultEntriesModel(IModel<? extends PaginatedResult<T>> parent)
	{
		super(parent);
	}

	@Override
	public List<T> getObject()
	{
		return this.getParentObject().getEntries();
	}

}
