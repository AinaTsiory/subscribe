package school.hei.subscribe.event;


import lombok.Getter;
import school.hei.subscribe.entity.Subscription;

@Getter
public class UserSubscribedEvent {

    private final Subscription subscription;

    public UserSubscribedEvent(Subscription subscription) {
        this.subscription = subscription;
    }
}