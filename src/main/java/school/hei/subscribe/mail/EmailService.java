package school.hei.subscribe.mail;


import jakarta.mail.internet.InternetAddress;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.hei.subscribe.entity.Course;
import school.hei.subscribe.entity.Subscription;
import school.hei.subscribe.entity.User;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final Mailer mailer;

    public void sendConfirmation(Subscription subscription) {

        User user = subscription.getUser();
        Course course = subscription.getCourse();

        try {

            Email email = new Email(
                    new InternetAddress(user.getEmail()),
                    List.of(),
                    List.of(),
                    "Confirmation d'inscription",
                    """
                    <h2>Bonjour %s %s,</h2>

                    <p>Votre inscription au cours <strong>%s</strong> a bien été enregistrée.</p>

                    <p>Date de début : %s</p>

                    <p>Merci !</p>
                    """
                            .formatted(
                                    user.getFirstName(),
                                    user.getLastName(),
                                    course.getTitle(),
                                    course.getStartDate()
                            ),
                    List.of()
            );

            mailer.accept(email);

        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'envoi de l'e-mail.", e);
        }
    }
}