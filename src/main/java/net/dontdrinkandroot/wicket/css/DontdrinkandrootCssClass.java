package net.dontdrinkandroot.wicket.css;

public enum DontdrinkandrootCssClass implements CssClass
{
	LIST_GROUP_PLAIN("list-group-plain");

	private String msgKey;


	private DontdrinkandrootCssClass(String msgKey)
	{
		this.msgKey = msgKey;
	}

	@Override
	public String getClassString()
	{
		return this.msgKey;
	}

}
