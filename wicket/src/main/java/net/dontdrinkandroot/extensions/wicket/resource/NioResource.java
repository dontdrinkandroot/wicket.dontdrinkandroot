package net.dontdrinkandroot.extensions.wicket.resource;

import org.apache.wicket.Application;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.http.flow.AbortWithHttpErrorCodeException;
import org.apache.wicket.request.resource.AbstractResource;
import org.apache.wicket.request.resource.PartWriterCallback;
import org.apache.wicket.util.time.Duration;
import org.apache.wicket.util.time.Time;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public abstract class NioResource extends AbstractResource
{
    @Override
    protected ResourceResponse newResourceResponse(Attributes attributes)
    {
        Path path = this.resolvePath(attributes);

        try {
            BasicFileAttributes fileAttributes = Files.readAttributes(path, BasicFileAttributes.class);
            if (fileAttributes.isDirectory()) {
                throw new AbortWithHttpErrorCodeException(404);
            }
            long size = fileAttributes.size();

            ResourceResponse resourceResponse = new ResourceResponse();
            resourceResponse.setContentType(getMimeType(path));
            resourceResponse.setAcceptRange(ContentRangeType.BYTES);
            resourceResponse.setContentLength(size);
            resourceResponse.setLastModified(Time.millis(fileAttributes.lastModifiedTime().toMillis()));
            resourceResponse.setCacheDuration(Duration.milliseconds(1));
            RequestCycle cycle = RequestCycle.get();
            Long startbyte = cycle.getMetaData(CONTENT_RANGE_STARTBYTE);
            Long endbyte = cycle.getMetaData(CONTENT_RANGE_ENDBYTE);
            resourceResponse.setWriteCallback(
                    new PartWriterCallback(Files.newInputStream(path), size, startbyte, endbyte));

            return resourceResponse;
        } catch (IOException e) {
            throw new WicketRuntimeException(e);
        }
    }

    public static String getMimeType(Path path) throws IOException
    {
        String mimeType = null;

        if (Application.exists()) {
            mimeType = Application.get().getMimeType(path.getFileName().toString());
        }

        if (mimeType == null) {
            mimeType = Files.probeContentType(path);
        }

        return mimeType;
    }

    protected abstract Path resolvePath(Attributes attributes);
}
