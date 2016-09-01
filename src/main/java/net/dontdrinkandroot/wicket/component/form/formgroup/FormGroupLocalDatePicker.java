package net.dontdrinkandroot.wicket.component.form.formgroup;

import net.dontdrinkandroot.wicket.bootstrap.component.form.formgroup.FormGroupFormComponent;
import net.dontdrinkandroot.wicket.bower.BootstrapDatetimepickerCssHeaderItem;
import net.dontdrinkandroot.wicket.bower.BootstrapDatetimepickerJsHeaderItem;
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

import java.time.LocalDate;
import java.util.Locale;

// TODO: Add output format localization
public class FormGroupLocalDatePicker extends FormGroupFormComponent<LocalDate, LocalDate, HiddenField<LocalDate>>
{
	private Label dateLabel;

	public FormGroupLocalDatePicker(String id, IModel<String> labelModel, IModel<LocalDate> model)
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
				target.add(FormGroupLocalDatePicker.this.dateLabel);
			}
		});
	}

	@Override
	protected HiddenField<LocalDate> createFormComponent(String id)
	{
		HiddenField<LocalDate> hiddenField = new HiddenField<LocalDate>(id, this.getModel(), LocalDate.class)
		{

			@Override
			public <C> IConverter<C> getConverter(Class<C> type)
			{
				if (LocalDate.class.isAssignableFrom(type)) {
					return (IConverter<C>) new AbstractConverter<LocalDate>()
					{

						@Override
						public LocalDate convertToObject(String value, Locale locale) throws ConversionException
						{
							return LocalDate.parse(value);
						}

						@Override
						protected Class<LocalDate> getTargetType()
						{
							return LocalDate.class;
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
		response.render(JavaScriptHeaderItem.forReference(new PackageResourceReference(this.getClass(),
				"datetimepicker.js"
		)));
		response.render(new OnLoadHeaderItem(String.format("initDateTimePicker('%s', '%s', '%s')",
				this.getMarkupId(),
				"YYYY-MM-DD",
				this.getLocale().getLanguage()
		)));
	}
}
