package school.hei.subscribe.endpoint.rest.dto;

import software.amazon.awssdk.annotations.NotNull;

import java.util.UUID;

public class SubscribeRequest {

    @NotNull
    private UUID userId;

    @NotNull
    private UUID courseId;

    protected SubscribeRequest() {
        // requis pour la désérialisation JSON
    }

    public SubscribeRequest(UUID userId, UUID courseId) {
        this.userId = userId;
        this.courseId = courseId;
    }

    public UUID getUserId() {
        return userId;
    }

    public UUID getCourseId() {
        return courseId;
    }
}
