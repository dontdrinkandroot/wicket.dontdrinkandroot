package net.dontdrinkandroot.extensions.wicket.validator;

import net.dontdrinkandroot.wicket.component.form.LocalDateTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.validation.AbstractFormValidator;
import org.apache.wicket.validation.ValidationError;

import java.time.LocalDate;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class EndDateAfterStartDateValidator extends AbstractFormValidator
{
    private final LocalDateTextField startDateField;

    private final LocalDateTextField endDateField;

    public EndDateAfterStartDateValidator(LocalDateTextField startDateField, LocalDateTextField endDateField)
    {
        this.startDateField = startDateField;
        this.endDateField = endDateField;
    }

    @Override
    public FormComponent<?>[] getDependentFormComponents()
    {
        return new LocalDateTextField[]{this.startDateField, this.endDateField};
    }

    @Override
    public void validate(Form<?> form)
    {
        LocalDate startDate = this.startDateField.getConvertedInput();
        LocalDate endDate = this.endDateField.getConvertedInput();
        if (null != startDate && null != endDate && endDate.isBefore(startDate)) {
            this.endDateField.error(new ValidationError("End Date must not be before Start Date"));
        }
    }
}
