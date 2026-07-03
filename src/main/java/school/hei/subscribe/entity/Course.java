package school.hei.subscribe.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {

    @Id
    @GeneratedValue
    private UUID id;

    private String title;
    private Instant startDate;
    private Instant endDate;

    @ManyToMany(mappedBy = "courses")
    @ToString.Exclude
    private Set<User> subscribers = new HashSet<>();
}
