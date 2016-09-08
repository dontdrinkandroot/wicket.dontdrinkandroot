package net.dontdrinkandroot.wicket.component.form.formgroup;

import net.dontdrinkandroot.wicket.bootstrap.component.form.formgroup.FormGroupFormComponent;
import net.dontdrinkandroot.wicket.bower.BootstrapDatetimepickerCssHeaderItem;
import net.dontdrinkandroot.wicket.bower.BootstrapDatetimepickerJsHeaderItem;
import net.dontdrinkandroot.wicket.model.TemporalAccessorFormatModel;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnLoadHeaderItem;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.HiddenField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.util.convert.ConversionException;
import org.apache.wicket.util.convert.IConverter;
import org.apache.wicket.util.convert.converter.AbstractConverter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

// TODO: Add output format localization
public class FormGroupLocalDateTimePicker extends FormGroupFormComponent<LocalDateTime, LocalDateTime, HiddenField<LocalDateTime>>
{
    private static String PATTERN = "yyyy-MM-dd HH:mm:ss";

    private Label dateLabel;

    public FormGroupLocalDateTimePicker(String id, IModel<String> labelModel, IModel<LocalDateTime> model)
    {
        super(id, labelModel, model);
    }

    @Override
    protected void createComponents()
    {
        super.createComponents();
        this.dateLabel = new Label("dateLabel", new TemporalAccessorFormatModel(this.getModel(), PATTERN));
        this.dateLabel.setOutputMarkupId(true);
    }

    @Override
    protected void addComponents()
    {
        super.addComponents();
        this.container.add(this.dateLabel);
    }

    @Override
    protected void addBehaviors()
    {
        super.addBehaviors();
        this.getFormComponent().add(new AjaxFormComponentUpdatingBehavior("change")
        {

            @Override
            protected void onUpdate(AjaxRequestTarget target)
            {
                target.add(FormGroupLocalDateTimePicker.this.dateLabel);
            }
        });
    }

    @Override
    protected HiddenField<LocalDateTime> createFormComponent(String id)
    {
        HiddenField<LocalDateTime> hiddenField =
                new HiddenField<LocalDateTime>(id, this.getModel(), LocalDateTime.class)
                {

                    @Override
                    public <C> IConverter<C> getConverter(Class<C> type)
                    {
                        if (LocalDateTime.class.isAssignableFrom(type)) {
                            return (IConverter<C>) new AbstractConverter<LocalDateTime>()
                            {
                                @Override
                                public LocalDateTime convertToObject(
                                        String value,
                                        Locale locale
                                ) throws ConversionException
                                {
                                    return LocalDateTime.parse(value, DateTimeFormatter.ofPattern(PATTERN));
                                }

                                @Override
                                protected Class<LocalDateTime> getTargetType()
                                {
                                    return LocalDateTime.class;
                                }
                            };
                        } else {
                            return super.getConverter(type);
                        }
                    }
                };
        return hiddenField;
    }

    @Override
    public void renderHead(IHeaderResponse response)
    {
        response.render(new BootstrapDatetimepickerJsHeaderItem());
        response.render(new BootstrapDatetimepickerCssHeaderItem());
        response.render(JavaScriptHeaderItem.forReference(new PackageResourceReference(
                this.getClass(),
                "datetimepicker.js"
        )));
        response.render(new OnLoadHeaderItem(String.format(
                "initDateTimePicker('%s', '%s', '%s')",
                this.getMarkupId(),
                "YYYY-MM-DD HH:mm:ss",
                this.getLocale().getLanguage()
        )));
    }
}
