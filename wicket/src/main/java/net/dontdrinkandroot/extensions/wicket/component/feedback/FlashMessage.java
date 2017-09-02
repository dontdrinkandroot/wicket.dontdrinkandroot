package net.dontdrinkandroot.extensions.wicket.component.feedback;

import java.io.Serializable;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class FlashMessage implements Serializable
{
    private long delay = 2500;

    private String content;

    public FlashMessage(String content)
    {
        this.content = content;
    }

    public FlashMessage(String content, long delay)
    {
        this.delay = delay;
        this.content = content;
    }

    public String getContent()
    {
        return this.content;
    }

    public long getDelay()
    {
        return this.delay;
    }

    @Override
    public String toString()
    {
        return this.content;
    }
}
