package school.hei.subscribe.service;

import jakarta.mail.internet.InternetAddress;
import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.function.Consumer;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import school.hei.subscribe.endpoint.event.model.SubscriptionCreated;
import school.hei.subscribe.file.bucket.BucketComponent;
import school.hei.subscribe.mail.Email;
import school.hei.subscribe.mail.Mailer;

@Service
@AllArgsConstructor
public class SubscriptionCreatedService implements Consumer<SubscriptionCreated> {

    private final Mailer mailer;
    private final BucketComponent bucketComponent;
    private final TicketHtmlBuilder ticketHtmlBuilder;
    private final TicketPdfService ticketPdfService;

    @SneakyThrows
    @Override
    public void accept(SubscriptionCreated event) {
        String html = ticketHtmlBuilder.buildHtml(event);
        File pdfFile = ticketPdfService.generatePdf(html, "ticket-" + event.getSubscriptionId());

        try {
            // 2. Upload dans le bucket POJA et récupère une URL pré-signée (valable 24h)
            String bucketKey = "tickets/" + event.getSubscriptionId() + ".pdf";
            bucketComponent.upload(pdfFile, bucketKey);
            String downloadUrl = bucketComponent.presign(bucketKey, Duration.ofDays(1)).toString();

            // 3. Envoie l'email de confirmation avec le lien de téléchargement
            InternetAddress recipient = new InternetAddress(event.getUserEmail());
            String subject = "Confirmation d'inscription - " + event.getCourseTitle();
            String body = "Bonjour " + event.getUserFirstName() + ",\n\n"
                + "Votre inscription au cours \"" + event.getCourseTitle() + "\" a bien été enregistrée.\n\n"
                + "Cliquez ici pour télécharger votre billet : " + downloadUrl + "\n\n"
                + "Merci et à bientôt !";

            mailer.accept(new Email(recipient, List.of(), List.of(), subject, body, List.of()));
        } finally {

            if (!pdfFile.delete()) {
                pdfFile.deleteOnExit();
            }
        }
    }
}
