package net.dontdrinkandroot.extension.wicket.component.button;

import net.dontdrinkandroot.wicket.choicerenderer.ShortMonthChoiceRenderer;
import net.dontdrinkandroot.wicket.model.IntegerRangeListModel;
import org.apache.wicket.model.IModel;


public class MonthDropDownChoiceButton extends DropDownChoiceButton<Integer>
{
    public MonthDropDownChoiceButton(String id, IModel<Integer> model)
    {
        super(id, model, new IntegerRangeListModel(0, 11), Integer.class);
    }

    @Override
    protected void onInitialize()
    {
        super.onInitialize();

        super.setChoiceRenderer(new ShortMonthChoiceRenderer(this.getLocale()));
    }
}
