package school.hei.subscribe.repository;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(
        name = "subscriptions",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "course_id"})
)
public class JSubscription {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private JUser user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id", nullable = false)
    private JCourse course;

    @Column(name = "subscribed_at", nullable = false)
    private Instant subscribedAt;

    protected JSubscription() {
        // requis par JPA
    }

    public JSubscription(JUser user, JCourse course, Instant subscribedAt) {
        this.user = user;
        this.course = course;
        this.subscribedAt = subscribedAt;
    }

    public UUID getId() {
        return id;
    }

    public JUser getUser() {
        return user;
    }

    public JCourse getCourse() {
        return course;
    }

    public Instant getSubscribedAt() {
        return subscribedAt;
    }
}