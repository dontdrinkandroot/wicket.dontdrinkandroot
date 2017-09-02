package net.dontdrinkandroot.extensions.wicket.css;

import net.dontdrinkandroot.wicket.css.CssClass;

public enum DontdrinkandrootCssClass implements CssClass
{
    BTN_ICON("btn-icon"),
    LIST_GROUP_PLAIN("list-group-plain"),
    MARGIN_TOP_NONE("margin-top-none"),
    MARGIN_BOTTOM_NONE("margin-bottom-none"),
    PADDING_TOP_NONE("padding-top-none"),
    PADDING_BOTTOM_NONE("padding-bottom-none"),
    FLASH_MESSAGES("flash-messages");

    private String msgKey;

    DontdrinkandrootCssClass(String msgKey)
    {
        this.msgKey = msgKey;
    }

    @Override
    public String getClassString()
    {
        return this.msgKey;
    }
}
