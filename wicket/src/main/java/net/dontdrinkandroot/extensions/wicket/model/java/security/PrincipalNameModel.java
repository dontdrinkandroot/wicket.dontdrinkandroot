package net.dontdrinkandroot.extensions.wicket.model.java.security;

import net.dontdrinkandroot.wicket.model.AbstractChainedReadonlyModel;
import org.apache.wicket.model.IModel;

import java.security.Principal;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class PrincipalNameModel extends AbstractChainedReadonlyModel<Principal, String>
{
    public PrincipalNameModel(IModel<? extends Principal> parent)
    {
        super(parent);
    }

    @Override
    public String getObject()
    {
        return this.getParentObject().getName();
    }
}
