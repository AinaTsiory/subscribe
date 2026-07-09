package school.hei.subscribe.repository;


import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = “courses”)
public class JCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = “title”, nullable = false)
    private String title;

    @Column(name = “start_date”, nullable = false)
    private Instant startDate;

    @Column(name = “end_date”, nullable = false)
    private Instant endDate;

    protected JCourse() {
        // requis par JPA
    }

    public JCourse(String title, Instant startDate, Instant endDate) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }
}