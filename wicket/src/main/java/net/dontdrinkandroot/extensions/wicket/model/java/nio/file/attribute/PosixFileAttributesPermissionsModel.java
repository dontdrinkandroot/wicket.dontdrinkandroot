package net.dontdrinkandroot.extensions.wicket.model.java.nio.file.attribute;

import net.dontdrinkandroot.wicket.model.AbstractChainedReadonlyModel;
import org.apache.wicket.model.IModel;

import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Set;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class PosixFileAttributesPermissionsModel extends AbstractChainedReadonlyModel<PosixFileAttributes, Set<PosixFilePermission>>
{
    public PosixFileAttributesPermissionsModel(IModel<? extends PosixFileAttributes> parent)
    {
        super(parent);
    }

    @Override
    public Set<PosixFilePermission> getObject()
    {
        return this.getParentObject().permissions();
    }
}
