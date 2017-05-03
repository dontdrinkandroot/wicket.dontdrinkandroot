package net.dontdrinkandroot.extensions.wicket.model.java.nio.file.attribute;

import net.dontdrinkandroot.wicket.model.AbstractChainedReadonlyModel;
import org.apache.wicket.model.IModel;

import java.nio.file.attribute.GroupPrincipal;
import java.nio.file.attribute.PosixFileAttributes;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class PosixFileAttributesGroupModel extends AbstractChainedReadonlyModel<PosixFileAttributes, GroupPrincipal>
{
    public PosixFileAttributesGroupModel(IModel<? extends PosixFileAttributes> parent)
    {
        super(parent);
    }

    @Override
    public GroupPrincipal getObject()
    {
        return this.getParentObject().group();
    }
}
