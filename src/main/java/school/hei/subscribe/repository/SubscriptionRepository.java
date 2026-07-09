package school.hei.subscribe.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SubscriptionRepository extends JpaRepository<JSubscription, UUID> {

    boolean existsByUserAndCourse(JUser user, JCourse course);
}