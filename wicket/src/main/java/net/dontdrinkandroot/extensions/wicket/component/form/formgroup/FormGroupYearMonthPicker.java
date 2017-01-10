package net.dontdrinkandroot.extensions.wicket.component.form.formgroup;

import net.dontdrinkandroot.extensions.wicket.bower.BootstrapDatetimepickerCssHeaderItem;
import net.dontdrinkandroot.extensions.wicket.bower.BootstrapDatetimepickerJsHeaderItem;
import net.dontdrinkandroot.wicket.bootstrap.component.form.formgroup.FormGroupFormComponent;
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

import java.time.YearMonth;
import java.util.Locale;

// TODO: Add output format localization
public class FormGroupYearMonthPicker extends FormGroupFormComponent<YearMonth, YearMonth, HiddenField<YearMonth>>
{
    private Label dateLabel;

    public FormGroupYearMonthPicker(String id, IModel<String> labelModel, IModel<YearMonth> model)
    {
        super(id, labelModel, model);
    }

    @Override
    protected void createComponents()
    {
        super.createComponents();
        this.dateLabel = new Label("dateLabel", this.getModel());
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
                target.add(FormGroupYearMonthPicker.this.dateLabel);
            }
        });
    }

    @Override
    protected HiddenField<YearMonth> createFormComponent(String id)
    {
        return new HiddenField<YearMonth>(id, this.getModel(), YearMonth.class)
        {
            @Override
            public <C> IConverter<C> getConverter(Class<C> type)
            {
                if (YearMonth.class.isAssignableFrom(type)) {
                    return (IConverter<C>) new AbstractConverter<YearMonth>()
                    {
                        @Override
                        public YearMonth convertToObject(String value, Locale locale) throws ConversionException
                        {
                            return YearMonth.parse(value);
                        }

                        @Override
                        protected Class<YearMonth> getTargetType()
                        {
                            return YearMonth.class;
                        }
                    };
                } else {
                    return super.getConverter(type);
                }
            }
        };
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
                "initDateTimePicker('%s', 'YYYY-MM', '%s', 'months')",
                this.getMarkupId(),
                this.getLocale().getLanguage()
        )));
    }
}
