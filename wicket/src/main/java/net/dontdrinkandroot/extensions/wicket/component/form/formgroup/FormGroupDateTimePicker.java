package net.dontdrinkandroot.extensions.wicket.component.form.formgroup;

import net.dontdrinkandroot.extensions.wicket.bower.BootstrapDatetimepickerCssHeaderItem;
import net.dontdrinkandroot.extensions.wicket.bower.BootstrapDatetimepickerJsHeaderItem;
import net.dontdrinkandroot.wicket.bootstrap.component.form.formgroup.FormGroupFormComponent;
import net.dontdrinkandroot.wicket.model.SimpleDateFormatModel;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnLoadHeaderItem;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.HiddenField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.util.convert.IConverter;
import org.apache.wicket.util.convert.converter.DateConverter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

// TODO: Add output format localization
public class FormGroupDateTimePicker extends FormGroupFormComponent<Date, Date, HiddenField<Date>>
{
    public enum Precision
    {
        YEAR("YYYY", "yyyy"),
        MONTH("YYYYMM", "yyyyMM"),
        DAY("YYYYMMDD", "yyyyMMdd"),
        HOUR("YYYYMMDDHH", "yyyyMMddHH"),
        MINUTE("YYYYMMDDHHmm", "yyyyMMddHHmm"),
        SECOND("YYYYMMDDHHmmss", "yyyyMMddHHmmss");

        private String momentFormat;

        private String converterFormat;

        private Precision(String momentFormat, String converterFormat)
        {
            this.momentFormat = momentFormat;
            this.converterFormat = converterFormat;
        }

        public String getConverterFormat()
        {
            return this.converterFormat;
        }

        public String getMomentFormat()
        {
            return this.momentFormat;
        }
    }

    private Precision precision = Precision.SECOND;

    private Label dateLabel;

    public FormGroupDateTimePicker(String id, IModel<String> labelModel, IModel<Date> model)
    {
        super(id, labelModel, model);
    }

    public FormGroupDateTimePicker(String id, IModel<String> labelModel, IModel<Date> model, Precision precision)
    {
        super(id, labelModel, model);
        this.precision = precision;
    }

    @Override
    protected void createComponents()
    {
        super.createComponents();
        this.dateLabel = new Label(
                "dateLabel",
                new SimpleDateFormatModel(this.getModel(), this.getOutputFormatPattern(), this.getLocale())
        );
        this.dateLabel.setOutputMarkupId(true);
    }

    protected String getOutputFormatPattern()
    {
        if (this.getLocale().getLanguage().equals(Locale.GERMAN.getLanguage())) {
            switch (this.precision) {
                case YEAR:
                    return "yyyy";
                case MONTH:
                    return "MMMM yyyy";
                case DAY:
                    return "EEE, d. MMMM yyyy";
                case HOUR:
                    return "EEE, d. MMMM yyyy, HH";
                case MINUTE:
                    return "EEE, d. MMMM yyyy, HH:mm";
                case SECOND:
                    return "EEE, d. MMMM yyyy, HH:mm:ss";
            }
        }

        if (this.getLocale().getLanguage().equals(Locale.ENGLISH.getLanguage())) {
            switch (this.precision) {
                case YEAR:
                    return "yyyy";
                case MONTH:
                    return "MMMM yyyy";
                case DAY:
                    return "EEE, MMMM d, yyyy";
                case HOUR:
                    return "EEE, MMMM d, yyyy, HH";
                case MINUTE:
                    return "EEE, MMMM d, yyyy, HH:mm";
                case SECOND:
                    return "EEE, MMMM d, yyyy, HH:mm:ss";
            }
        }

        switch (this.precision) {
            case YEAR:
                return "yyyy";
            case MONTH:
                return "yyyy-MM";
            case DAY:
                return "yyyy-MM-dd";
            case HOUR:
                return "yyyy-MM-dd HH";
            case MINUTE:
                return "yyyy-MM-dd HH:mm";
            case SECOND:
                return "yyyy-MM-dd HH:mm:ss";
        }

        return this.precision.getConverterFormat();
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
                target.add(FormGroupDateTimePicker.this.dateLabel);
            }
        });
    }

    @Override
    protected HiddenField<Date> createFormComponent(String id)
    {
        HiddenField<Date> hiddenField = new HiddenField<Date>(id, this.getModel(), Date.class)
        {

            @Override
            public <C> IConverter<C> getConverter(Class<C> type)
            {
                if (Date.class.isAssignableFrom(type)) {
                    return (IConverter<C>) new DateConverter()
                    {

                        @Override
                        public DateFormat getDateFormat(Locale locale)
                        {
                            return new SimpleDateFormat(FormGroupDateTimePicker.this.precision.getConverterFormat());
                        }

                        ;
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
        response.render(
                JavaScriptHeaderItem.forReference(new PackageResourceReference(this.getClass(), "datetimepicker.js")));
        response.render(
                new OnLoadHeaderItem(
                        String.format(
                                "initDateTimePicker('%s', '%s', '%s')",
                                this.getMarkupId(),
                                this.precision.getMomentFormat(),
                                this.getLocale().getLanguage()
                        )));
    }
}
