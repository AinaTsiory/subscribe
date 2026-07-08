package school.hei.subscribe.service;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;
import school.hei.subscribe.endpoint.rest.event.model.SubscriptionCreated;

@Component
public class TicketHtmlBuilder {

    private static final DateTimeFormatter FORMATTER =
        DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").withZone(ZoneId.of("Indian/Antananarivo"));

    public String buildHtml(SubscriptionCreated event) {
        return "<html>"
            + "<head><style>"
            + "body { font-family: Arial, sans-serif; padding: 40px; }"
            + ".ticket { border: 2px solid #333; border-radius: 12px; padding: 24px; max-width: 500px; }"
            + ".title { font-size: 22px; font-weight: bold; margin-bottom: 16px; }"
            + ".label { color: #666; font-size: 12px; text-transform: uppercase; margin-top: 12px; }"
            + ".value { font-size: 16px; }"
            + "</style></head>"
            + "<body>"
            + "<div class=\"ticket\">"
            + "<div class=\"title\">Billet d'inscription</div>"
            + "<div class=\"label\">Cours</div>"
            + "<div class=\"value\">" + escape(event.getCourseTitle()) + "</div>"
            + "<div class=\"label\">Participant</div>"
            + "<div class=\"value\">" + escape(event.getUserFirstName()) + "</div>"
            + "<div class=\"label\">Référence</div>"
            + "<div class=\"value\">" + event.getSubscriptionId() + "</div>"
            + "<div class=\"label\">Date d'inscription</div>"
            + "<div class=\"value\">" + FORMATTER.format(event.getSubscribedAt()) + "</div>"
            + "</div>"
            + "</body></html>";
    }

    private String escape(String value) {
        if (value == null) {
            return "";
        }
        return value.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }
}
