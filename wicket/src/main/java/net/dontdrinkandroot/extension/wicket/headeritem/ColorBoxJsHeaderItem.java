package net.dontdrinkandroot.extension.wicket.headeritem;

import org.apache.wicket.Application;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.JavaScriptUrlReferenceHeaderItem;
import org.apache.wicket.request.resource.ResourceReference;

import java.util.Arrays;
import java.util.List;


public class ColorBoxJsHeaderItem extends JavaScriptUrlReferenceHeaderItem
{
    public ColorBoxJsHeaderItem()
    {
        super(
                "http://design.dontdrinkandroot.net/js/colorbox/jquery.colorbox-1.3.32.min.js",
                "colorbox.js",
                false,
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
