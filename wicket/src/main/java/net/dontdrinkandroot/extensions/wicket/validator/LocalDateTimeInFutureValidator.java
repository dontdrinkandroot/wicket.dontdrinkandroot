package net.dontdrinkandroot.extensions.wicket.validator;

import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;

import java.time.LocalDateTime;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class LocalDateTimeInFutureValidator extends Behavior implements IValidator<LocalDateTime>
{
    @Override
    public void validate(IValidatable<LocalDateTime> validatable)
    {
        LocalDateTime dateTime = validatable.getValue();
        if (dateTime.isBefore(LocalDateTime.now())) {
            validatable.error(new ValidationError().addKey("ddr.validation.time_in_past"));
        }
    }
}
