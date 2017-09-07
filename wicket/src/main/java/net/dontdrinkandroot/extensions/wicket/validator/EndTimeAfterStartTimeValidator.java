package net.dontdrinkandroot.extensions.wicket.validator;

import net.dontdrinkandroot.wicket.component.form.LocalDateTimeTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.validation.AbstractFormValidator;
import org.apache.wicket.validation.ValidationError;

import java.time.LocalDateTime;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class EndTimeAfterStartTimeValidator extends AbstractFormValidator
{
    private final LocalDateTimeTextField startTimeField;

    private final LocalDateTimeTextField endTimeField;

    public EndTimeAfterStartTimeValidator(LocalDateTimeTextField startTimeField, LocalDateTimeTextField endTimeField)
    {
        this.startTimeField = startTimeField;
        this.endTimeField = endTimeField;
    }

    @Override
    public FormComponent<?>[] getDependentFormComponents()
    {
        return new LocalDateTimeTextField[]{this.startTimeField, this.endTimeField};
    }

    @Override
    public void validate(Form<?> form)
    {
        LocalDateTime startDate = this.startTimeField.getConvertedInput();
        LocalDateTime endDate = this.endTimeField.getConvertedInput();
        if (null != startDate && null != endDate && endDate.isBefore(startDate)) {
            this.endTimeField.error(new ValidationError().addKey("ddr.validation.end_time_before_start_time"));
        }
    }
}
