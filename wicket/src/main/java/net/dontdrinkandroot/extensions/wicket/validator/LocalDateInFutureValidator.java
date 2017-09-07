package net.dontdrinkandroot.extensions.wicket.validator;

import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;

import java.time.LocalDate;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class LocalDateInFutureValidator extends Behavior implements IValidator<LocalDate>
{
    @Override
    public void validate(IValidatable<LocalDate> validatable)
    {
        LocalDate date = validatable.getValue();
        if (date.isBefore(LocalDate.now())) {
            validatable.error(new ValidationError().addKey("ddr.validation.date_in_past"));
        }
    }
}
