package net.dontdrinkandroot.wicket.bootstrap.headeritem;

import org.apache.wicket.Application;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.JavaScriptUrlReferenceHeaderItem;
import org.apache.wicket.request.resource.ResourceReference;

import java.util.Arrays;
import java.util.List;


public class DontdrinkandrootBootstrap31JsHeaderItem extends JavaScriptUrlReferenceHeaderItem
{

    public DontdrinkandrootBootstrap31JsHeaderItem(boolean defer)
    {
        super(
                "http://static.dontdrinkandroot.net/bootstrap/3.1/bootstrap.min.js",
                "bootstrap-3.1.js",
                defer,
                null,
                null
        );
    }

    @Override
    public List<HeaderItem> getDependencies()
    {
        ResourceReference jQueryReference = Application.get().getJavaScriptLibrarySettings().getJQueryReference();
        HeaderItem jQueryHeaderItem = JavaScriptHeaderItem.forReference(jQueryReference);
        return Arrays.asList(jQueryHeaderItem);
    }
}
