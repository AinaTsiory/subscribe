package school.hei.subscribe.endpoint.event.model;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import school.hei.subscribe.endpoint.event.model.PojaEvent;

@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@EqualsAndHashCode(callSuper = false)
@ToString
public class SubscriptionCreated extends PojaEvent {

    private UUID subscriptionId;
    private String userEmail;
    private String userFirstName;
    private String courseTitle;
    private Instant subscribedAt;

    @Override
    public Duration maxConsumerDuration() {
        return Duration.ofSeconds(45);
    }

    @Override
    public Duration maxConsumerBackoffBetweenRetries() {
        return Duration.ofSeconds(30);
    }
}
