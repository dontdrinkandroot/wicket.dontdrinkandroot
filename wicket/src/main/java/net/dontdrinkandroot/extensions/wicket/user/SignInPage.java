package net.dontdrinkandroot.extensions.wicket.user;

import net.dontdrinkandroot.extensions.wicket.bootstrap.headeritem.DontdrinkandrootBootstrap33JsHeaderItem;
import net.dontdrinkandroot.extensions.wicket.bootstrap.headeritem.DontdrinkandrootBootstrapCssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class SignInPage extends net.dontdrinkandroot.wicket.extras.page.SignInPage
{
    public SignInPage(PageParameters parameters)
    {
        super(parameters);
    }

    @Override
    public void renderHead(IHeaderResponse response)
    {
        response.render(new DontdrinkandrootBootstrapCssHeaderItem());
        response.render(new DontdrinkandrootBootstrap33JsHeaderItem(true));
    }
}
