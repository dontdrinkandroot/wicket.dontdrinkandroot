package net.dontdrinkandroot.extension.wicket.bower;

import net.dontdrinkandroot.extension.wicket.bootstrap.headeritem.DontdrinkandrootBootstrap33JsHeaderItem;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.request.resource.PackageResourceReference;

import java.util.ArrayList;
import java.util.List;


public class BootstrapDatetimepickerJsHeaderItem extends JavaScriptReferenceHeaderItem
{
    public BootstrapDatetimepickerJsHeaderItem()
    {
        super(
                new PackageResourceReference(
                        BootstrapDatetimepickerJsHeaderItem.class,
                        "eonasdan-bootstrap-datetimepicker/build/js/bootstrap-datetimepicker.min.js"
                ),
                null,
                "bootstrap-datetimepicker.min.js",
                true,
                null,
                null
        );
    }

    @Override
    public List<HeaderItem> getDependencies()
    {
        List<HeaderItem> deps = new ArrayList<HeaderItem>();
        deps.add(new DontdrinkandrootBootstrap33JsHeaderItem(true));
        deps.add(new MomentJsHeaderItem());

        return deps;
    }
}
