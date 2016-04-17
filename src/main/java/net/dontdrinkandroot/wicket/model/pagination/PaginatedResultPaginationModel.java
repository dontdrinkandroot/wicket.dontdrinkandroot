package net.dontdrinkandroot.wicket.model.pagination;

import org.apache.wicket.model.IModel;

import net.dontdrinkandroot.utils.pagination.PaginatedResult;
import net.dontdrinkandroot.utils.pagination.Pagination;
import net.dontdrinkandroot.wicket.model.AbstractChainedModel;


public class PaginatedResultPaginationModel extends AbstractChainedModel<PaginatedResult<?>, Pagination>
{

	public PaginatedResultPaginationModel(IModel<? extends PaginatedResult<?>> parent)
	{
		super(parent);
	}

	@Override
	public Pagination getObject()
	{
		return this.getParentObject().getPagination();
	}

}
