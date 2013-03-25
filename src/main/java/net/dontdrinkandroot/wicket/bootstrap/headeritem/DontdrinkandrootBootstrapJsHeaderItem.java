package net.dontdrinkandroot.wicket.bootstrap.headeritem;

import java.util.Collections;

import org.apache.wicket.ajax.WicketEventJQueryResourceReference;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.JavaScriptUrlReferenceHeaderItem;


public class DontdrinkandrootBootstrapJsHeaderItem extends JavaScriptUrlReferenceHeaderItem {

	public DontdrinkandrootBootstrapJsHeaderItem(boolean defer) {

		super("http://design.dontdrinkandroot.net/js/bootstrap/bootstrap-2.3.min.js", "bootstrap.js", defer, null, null);
	}


	@Override
	public Iterable<? extends HeaderItem> getDependencies() {

		HeaderItem jQuery = JavaScriptHeaderItem.forReference(WicketEventJQueryResourceReference.get());

		return Collections.singletonList(jQuery);
	}
}
