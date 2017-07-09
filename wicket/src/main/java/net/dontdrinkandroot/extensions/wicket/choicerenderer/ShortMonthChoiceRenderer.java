package net.dontdrinkandroot.extensions.wicket.choicerenderer;

import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;

import java.text.DateFormatSymbols;
import java.util.List;
import java.util.Locale;

/**
 * Choice renderer that converts Integer values to the corresponding shortMonths String, based on the given locale.
 *
 * @author Philip Washington Sorst <philip@sorst.net>
 * @see DateFormatSymbols#getShortMonths()
 */
public class ShortMonthChoiceRenderer implements IChoiceRenderer<Integer>
{
    private final String[] months;

    public ShortMonthChoiceRenderer(Locale locale)
    {
        this.months = new DateFormatSymbols(locale).getShortMonths();
    }

    @Override
    public Object getDisplayValue(Integer object)
    {
        if (null == object) {
            return "";
        }

        return this.months[object];
    }

    @Override
    public String getIdValue(Integer object, int index)
    {
        return Integer.toString(index);
    }

    @Override
    public Integer getObject(String id, IModel<? extends List<? extends Integer>> choices)
    {
        final List<? extends Integer> choicesObject = choices.getObject();
        for (int index = 0; index < choicesObject.size(); index++) {
            // Get next choice
            final Integer choice = choicesObject.get(index);
            if (this.getIdValue(choice, index).equals(id)) {
                return choice;
            }
        }

        return null;
    }
}