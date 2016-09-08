package net.dontdrinkandroot.wicket.headeritem;

import org.apache.wicket.markup.head.JavaScriptUrlReferenceHeaderItem;


public class JQuery2JsHeaderItem extends JavaScriptUrlReferenceHeaderItem
{

    public JQuery2JsHeaderItem()
    {
        super("https://static.dontdrinkandroot.net/jquery/2/jquery.min.js", "jquery-2.js", false, null, null);
    }

}
