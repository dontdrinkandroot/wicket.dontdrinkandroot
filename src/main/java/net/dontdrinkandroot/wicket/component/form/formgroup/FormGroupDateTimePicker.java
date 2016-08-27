package net.dontdrinkandroot.wicket.component.form.formgroup;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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

import net.dontdrinkandroot.wicket.bootstrap.component.form.formgroup.FormGroupFormComponent;
import net.dontdrinkandroot.wicket.bower.BootstrapDatetimepickerCssHeaderItem;
import net.dontdrinkandroot.wicket.bower.BootstrapDatetimepickerJsHeaderItem;
import net.dontdrinkandroot.wicket.model.SimpleDateFormatModel;


public class FormGroupDateTimePicker extends FormGroupFormComponent<Date, Date, HiddenField<Date>>
{

	private Label dateLabel;


	public FormGroupDateTimePicker(String id, IModel<String> labelModel, IModel<Date> model)
	{
		super(id, labelModel, model);
	}

	@Override
	protected void createComponents()
	{
		super.createComponents();
		this.dateLabel = new Label(
				"dateLabel",
				new SimpleDateFormatModel(this.getModel(), "EEE, d. MMMM yyyy, HH:mm", this.getLocale()));
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
		this.getFormComponent().add(new AjaxFormComponentUpdatingBehavior("change") {

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
		HiddenField<Date> hiddenField = new HiddenField<Date>(id, this.getModel(), Date.class) {

			@Override
			public <C> IConverter<C> getConverter(Class<C> type)
			{
				if (Date.class.isAssignableFrom(type)) {
					return (IConverter<C>) new DateConverter() {

						@Override
						public DateFormat getDateFormat(Locale locale)
						{
							return new SimpleDateFormat("yyyyMMddHHmm");
						};
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
		response.render(new OnLoadHeaderItem(String.format("initDateTimePicker('%s')", this.getMarkupId())));
	};
}
