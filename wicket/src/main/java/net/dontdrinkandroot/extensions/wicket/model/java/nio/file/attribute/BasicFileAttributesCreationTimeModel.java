package net.dontdrinkandroot.extensions.wicket.model.java.nio.file.attribute;

import net.dontdrinkandroot.wicket.model.AbstractChainedReadonlyModel;
import org.apache.wicket.model.IModel;

import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.PosixFileAttributes;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class BasicFileAttributesCreationTimeModel extends AbstractChainedReadonlyModel<BasicFileAttributes, FileTime>
{
    public BasicFileAttributesCreationTimeModel(IModel<? extends PosixFileAttributes> parent)
    {
        super(parent);
    }

    @Override
    public FileTime getObject()
    {
        return this.getParentObject().creationTime();
    }
}
