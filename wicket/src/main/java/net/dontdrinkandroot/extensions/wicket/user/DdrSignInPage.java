package net.dontdrinkandroot.extensions.wicket.user;

import net.dontdrinkandroot.extensions.wicket.bootstrap.headeritem.DontdrinkandrootBootstrap33JsHeaderItem;
import net.dontdrinkandroot.extensions.wicket.bootstrap.headeritem.DontdrinkandrootBootstrapCssHeaderItem;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public abstract class DdrSignInPage extends net.dontdrinkandroot.wicket.extras.page.SignInPage
{
    public DdrSignInPage(PageParameters parameters)
    {
        super(parameters);
    }

    @Override
    protected HeaderItem getBootstrapJavaScriptHeaderItem()
    {
        return new DontdrinkandrootBootstrap33JsHeaderItem(true);
    }

    @Override
    protected HeaderItem getBootstrapCssHeaderItem()
    {
        return new DontdrinkandrootBootstrapCssHeaderItem();
    }
}
