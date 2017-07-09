package net.dontdrinkandroot.extensions.wicket.choicerenderer;

import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;

import java.util.List;
import java.util.Locale;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class CountryCodeCountryNameChoiceRenderer implements IChoiceRenderer<String>
{
    private Locale displayLocale;

    public CountryCodeCountryNameChoiceRenderer()
    {
    }

    public CountryCodeCountryNameChoiceRenderer(Locale displayLocale)
    {
        this.displayLocale = displayLocale;
    }

    @Override
    public Object getDisplayValue(String object)
    {
        if (null != this.displayLocale) {
            return new Locale("", object).getDisplayCountry(this.displayLocale);
        }

        return new Locale("", object).getDisplayCountry();
    }

    @Override
    public String getIdValue(String object, int index)
    {
        return object;
    }

    @Override
    public String getObject(String id, IModel<? extends List<? extends String>> choices)
    {
        return id;
    }
}
