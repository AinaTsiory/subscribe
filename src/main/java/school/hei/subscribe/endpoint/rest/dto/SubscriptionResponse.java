package school.hei.subscribe.endpoint.rest.dto;

import school.hei.subscribe.entity.Subscription;

import java.time.Instant;
import java.util.UUID;

public class SubscriptionResponse {

    private final UUID id;
    private final UUID userId;
    private final UUID courseId;
    private final Instant subscribedAt;

    public SubscriptionResponse(Subscription subscription) {
        this.id = subscription.getId();
        this.userId = subscription.getUserId();
        this.courseId = subscription.getCourseId();
        this.subscribedAt = subscription.getSubscribedAt();
    }

    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public UUID getCourseId() {
        return courseId;
    }

    public Instant getSubscribedAt() {
        return subscribedAt;
    }
}
