package net.dontdrinkandroot.wicket.bootstrap.pagination;

import org.apache.wicket.markup.html.navigation.paging.IPageable;
import org.apache.wicket.model.Model;

import net.dontdrinkandroot.wicket.bootstrap.behavior.IconBehavior;
import net.dontdrinkandroot.wicket.bootstrap.component.pagination.AbstractPageLinkItem;
import net.dontdrinkandroot.wicket.bootstrap.css.FontAwesomeIcon;
import net.dontdrinkandroot.wicket.bootstrap.css.FontAwesomeIconClass;
import net.dontdrinkandroot.wicket.bootstrap.css.PaginationSize;


public class AjaxPaginationPanel extends net.dontdrinkandroot.wicket.bootstrap.component.pagination.AjaxPaginationPanel
{

	public AjaxPaginationPanel(String id, IPageable pageable)
	{
		super(id, pageable);
	}

	public AjaxPaginationPanel(String id, IPageable pageable, PaginationSize size)
	{
		super(id, pageable, size);
	}

	@Override
	protected AbstractPageLinkItem createFirstPageItem(String id)
	{
		AbstractPageLinkItem firstPageLinkItem = super.createFirstPageItem(id);
		firstPageLinkItem.setLabel(new Model<String>());
		firstPageLinkItem.getLink().add(new IconBehavior(new FontAwesomeIcon(FontAwesomeIconClass.ANGLE_DOUBLE_LEFT)));

		return firstPageLinkItem;
	}

	@Override
	protected AbstractPageLinkItem createPrevPageItem(String id)
	{
		AbstractPageLinkItem prevPageItem = super.createPrevPageItem(id);
		prevPageItem.setLabel(new Model<String>());
		prevPageItem.getLink().add(new IconBehavior(new FontAwesomeIcon(FontAwesomeIconClass.ANGLE_LEFT)));

		return prevPageItem;
	}

	@Override
	protected AbstractPageLinkItem createNextPageItem(String id)
	{
		AbstractPageLinkItem nextPageItem = super.createNextPageItem(id);
		nextPageItem.setLabel(new Model<String>());
		nextPageItem.getLink().add(new IconBehavior(new FontAwesomeIcon(FontAwesomeIconClass.ANGLE_RIGHT)));

		return nextPageItem;
	}

	@Override
	protected AbstractPageLinkItem createLastPageItem(String id)
	{
		AbstractPageLinkItem lastPageLinkItem = super.createLastPageItem(id);
		lastPageLinkItem.setLabel(new Model<String>());
		lastPageLinkItem.getLink().add(new IconBehavior(new FontAwesomeIcon(FontAwesomeIconClass.ANGLE_DOUBLE_RIGHT)));

		return lastPageLinkItem;
	}
}
