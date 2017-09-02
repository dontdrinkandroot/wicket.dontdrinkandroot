package net.dontdrinkandroot.extensions.wicket.component.feedback;

import net.dontdrinkandroot.extensions.wicket.css.DontdrinkandrootCssClass;
import net.dontdrinkandroot.wicket.bootstrap.component.feedback.FeedbackPanel;
import net.dontdrinkandroot.wicket.css.CssClass;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestHandler;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.feedback.FeedbackCollector;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.FeedbackMessagesModel;
import org.apache.wicket.feedback.IFeedbackMessageFilter;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.panel.Panel;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class FlashMessagePanel extends Panel
{
    private final FeedbackMessagesModel feedbackMessagesModel;

    public FlashMessagePanel(String id)
    {
        super(id);
        this.feedbackMessagesModel = new FeedbackMessagesModel(this)
        {
            @Override
            protected List<FeedbackMessage> collectMessages(
                    Component pageResolvingComponent,
                    IFeedbackMessageFilter filter
            )
            {
                return new FeedbackCollector()
                {
                    @Override
                    protected boolean shouldRecurseInto(Component component)
                    {
                        return false;
                    }
                }.collect();
            }
        };
        this.setOutputMarkupId(true);
        this.setMarkupId(DontdrinkandrootCssClass.FLASH_MESSAGES.getClassString());
    }

    @Override
    public void onEvent(IEvent<?> event)
    {
        super.onEvent(event);

        if (event.getPayload() instanceof AjaxRequestHandler) {
            AjaxRequestHandler ajaxRequestHandler = (AjaxRequestHandler) event.getPayload();
            List<FeedbackMessage> messages = this.feedbackMessagesModel.getObject();
            for (FeedbackMessage message : messages) {
                ajaxRequestHandler.appendJavaScript(this.createScript(message));
                message.markRendered();
            }
        }
    }

    @Override
    public void renderHead(IHeaderResponse response)
    {
        super.renderHead(response);

        List<FeedbackMessage> messages = this.feedbackMessagesModel.getObject();
        for (FeedbackMessage message : messages) {
            response.render(new OnDomReadyHeaderItem(this.createScript(message)));
            message.markRendered();
        }
    }

    private CharSequence createScript(FeedbackMessage message)
    {
        CssClass cssClass = FeedbackPanel.messageToBootstrapAlertCss(message);
        Serializable payload = message.getMessage();

        String id = "message-" + UUID.randomUUID().toString();
        String alert = String.format(
                "<div id=\"%s\" class=\"%s\">%s</div>",
                id,
                cssClass.getClassString(),
                payload.toString()
        );
        String create = String.format("$('#%s').append('%s');", this.getMarkupId(), alert);
        String slideUpAndRemove =
                String.format(
                        "$('#%s').delay(%d).slideUp(function() {$(this).remove();});",
                        id,
                        this.resolveDelay(payload)
                );

        return create + slideUpAndRemove;
    }

    private long resolveDelay(Serializable payload)
    {
        if (payload instanceof FlashMessage) {
            return ((FlashMessage) payload).getDelay();
        }

        return 2500L;
    }

    @Override
    public void detachModels()
    {
        super.detachModels();
        this.feedbackMessagesModel.detach();
    }
}
