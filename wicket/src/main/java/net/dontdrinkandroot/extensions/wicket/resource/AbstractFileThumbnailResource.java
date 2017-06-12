/*
 * Copyright (C) 2012-2016 Philip Washington Sorst <philip@sorst.net>
 * and individual contributors as indicated
 * by the @authors tag.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.dontdrinkandroot.extensions.wicket.resource;

import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.request.http.WebResponse;
import org.apache.wicket.request.resource.AbstractResource;
import org.apache.wicket.util.io.IOUtils;
import org.apache.wicket.util.time.Duration;
import org.apache.wicket.util.time.Time;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public abstract class AbstractFileThumbnailResource extends AbstractResource
{
    private static final long serialVersionUID = 1L;

    @Override
    protected ResourceResponse newResourceResponse(final Attributes attributes)
    {
        final File file = this.resolveFile(attributes);

        final Integer width = this.resolveWidth(attributes);

        final byte[] imageBytes;
        try {
            if (width == null) {
                final FileInputStream fis = new FileInputStream(file);
                final ByteArrayOutputStream imageBytesStream = new ByteArrayOutputStream();
                IOUtils.copy(fis, imageBytesStream);
                imageBytes = imageBytesStream.toByteArray();
                IOUtils.closeQuietly(fis);
            } else {
                final BufferedImage bufferedImage = ImageIO.read(file);
                final int oldWidth = bufferedImage.getWidth();
                final int oldHeight = bufferedImage.getHeight();
                final int newWidth = width;
                final int newHeight = newWidth * oldHeight / oldWidth;
                final BufferedImage scaledImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
                final Graphics2D g2d = (Graphics2D) scaledImage.getGraphics();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.drawImage(bufferedImage, 0, 0, newWidth, newHeight, 0, 0, oldWidth, oldHeight, null);
                g2d.dispose();
                final ByteArrayOutputStream imageBytesStream = new ByteArrayOutputStream();
                ImageIO.write(scaledImage, "jpg", imageBytesStream);
                imageBytes = imageBytesStream.toByteArray();
            }
        } catch (final IOException e) {
            throw new WicketRuntimeException(e);
        }

        final ResourceResponse resourceResponse = new ResourceResponse()
        {
            @Override
            public WriteCallback getWriteCallback()
            {
                return new WriteCallback()
                {
                    @Override
                    public void writeData(final Attributes attributes)
                    {
                        try {
                            this.writeStream(attributes, new ByteArrayInputStream(imageBytes));
                        } catch (IOException e) {
                            throw new WicketRuntimeException(e);
                        }
                    }
                };
            }
        };
        resourceResponse.setContentType("image/jpeg");
        resourceResponse.setLastModified(Time.millis(file.lastModified()));
        resourceResponse.setCacheDuration(this.getCacheDuration());
        resourceResponse.setContentLength(imageBytes.length);

        return resourceResponse;
    }

    protected Duration getCacheDuration()
    {
        return WebResponse.MAX_CACHE_DURATION;
    }

    protected abstract Integer resolveWidth(Attributes attributes);

    protected abstract File resolveFile(Attributes attributes);
}
