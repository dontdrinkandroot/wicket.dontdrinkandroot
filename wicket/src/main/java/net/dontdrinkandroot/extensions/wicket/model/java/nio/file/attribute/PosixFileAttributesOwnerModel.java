package net.dontdrinkandroot.extensions.wicket.model.java.nio.file.attribute;

import net.dontdrinkandroot.wicket.model.AbstractChainedReadonlyModel;
import org.apache.wicket.model.IModel;

import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.UserPrincipal;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class PosixFileAttributesOwnerModel extends AbstractChainedReadonlyModel<PosixFileAttributes, UserPrincipal>
{
    public PosixFileAttributesOwnerModel(IModel<? extends PosixFileAttributes> parent)
    {
        super(parent);
    }

    @Override
    public UserPrincipal getObject()
    {
        return this.getParentObject().owner();
    }
}
