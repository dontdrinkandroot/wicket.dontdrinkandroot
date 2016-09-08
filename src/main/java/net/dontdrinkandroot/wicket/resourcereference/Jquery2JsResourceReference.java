package net.dontdrinkandroot.wicket.resourcereference;

import org.apache.wicket.request.Url;
import org.apache.wicket.request.resource.UrlResourceReference;


public class Jquery2JsResourceReference extends UrlResourceReference
{

    public Jquery2JsResourceReference()
    {
        super(Url.parse("https://static.dontdrinkandroot.net/jquery/2/jquery.min.js"));
    }

}
