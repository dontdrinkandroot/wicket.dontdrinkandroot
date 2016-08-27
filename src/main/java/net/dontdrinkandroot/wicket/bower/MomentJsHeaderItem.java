package net.dontdrinkandroot.wicket.bower;

import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.request.resource.PackageResourceReference;


public class MomentJsHeaderItem extends JavaScriptReferenceHeaderItem
{

	public MomentJsHeaderItem()
	{
		super(
				new PackageResourceReference(MomentJsHeaderItem.class, "moment/min/moment-with-locales.min.js"),
				null,
				"moment-with-locales.min.js",
				true,
				null,
				null);
	}
}
