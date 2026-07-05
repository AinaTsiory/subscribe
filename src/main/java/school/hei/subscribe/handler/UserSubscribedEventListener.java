package school.hei.subscribe.handler;


import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import school.hei.subscribe.event.UserSubscribedEvent;
import school.hei.subscribe.mail.EmailService;

@Component
@RequiredArgsConstructor
public class UserSubscribedEventListener {

    private final EmailService emailService;

    @EventListener
    public void handle(UserSubscribedEvent event) {
        emailService.sendConfirmation(event.getSubscription());
    }
}