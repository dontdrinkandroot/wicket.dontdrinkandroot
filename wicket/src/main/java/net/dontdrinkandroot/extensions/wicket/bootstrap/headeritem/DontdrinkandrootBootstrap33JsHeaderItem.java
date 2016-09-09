package net.dontdrinkandroot.extensions.wicket.bootstrap.headeritem;

import org.apache.wicket.Application;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.JavaScriptUrlReferenceHeaderItem;
import org.apache.wicket.request.resource.ResourceReference;

import java.util.Arrays;
import java.util.List;

public class DontdrinkandrootBootstrap33JsHeaderItem extends JavaScriptUrlReferenceHeaderItem
{
    public DontdrinkandrootBootstrap33JsHeaderItem(boolean defer)
    {
        super(
                "https://static.dontdrinkandroot.net/bootstrap/3.3/bootstrap.min.js",
                "bootstrap-3.3.js",
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
