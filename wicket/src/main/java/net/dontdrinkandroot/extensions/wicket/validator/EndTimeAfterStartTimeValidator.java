package net.dontdrinkandroot.extensions.wicket.validator;

import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.model.IModel;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;

import java.time.LocalDateTime;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class EndTimeAfterStartTimeValidator extends Behavior implements IValidator<LocalDateTime>
{
    private IModel<LocalDateTime> startTimeModel;

    public EndTimeAfterStartTimeValidator(IModel<LocalDateTime> startTimeModel)
    {
        this.startTimeModel = startTimeModel;
    }

    @Override
    public void validate(IValidatable<LocalDateTime> validatable)
    {
        LocalDateTime startTime = this.startTimeModel.getObject();
        LocalDateTime endTime = validatable.getValue();

        if (null != startTime && null != endTime && !endTime.isAfter(startTime)) {
            validatable.error(new ValidationError().addKey("ddr.validation.end_time_before_start_time"));
        }
    }
}
