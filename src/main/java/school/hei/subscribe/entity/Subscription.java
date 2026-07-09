package school.hei.subscribe.entity;

import java.time.Instant;
import java.util.UUID;

public class Subscription {

    private final UUID id;
    private final UUID userId;
    private final UUID courseId;
    private final Instant subscribedAt;

    public Subscription(UUID id, UUID userId, UUID courseId, Instant subscribedAt) {
        this.id = id;
        this.userId = userId;
        this.courseId = courseId;
        this.subscribedAt = subscribedAt;
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
