package net.dontdrinkandroot.wicket.bower;

import org.apache.wicket.markup.head.CssReferenceHeaderItem;
import org.apache.wicket.request.resource.PackageResourceReference;


public class BootstrapDatetimepickerCssHeaderItem extends CssReferenceHeaderItem
{

	public BootstrapDatetimepickerCssHeaderItem()
	{
		super(
				new PackageResourceReference(
						BootstrapDatetimepickerCssHeaderItem.class,
						"eonasdan-bootstrap-datetimepicker/build/css/bootstrap-datetimepicker.min.css"),
				null,
				null,
				null);
	}
}
