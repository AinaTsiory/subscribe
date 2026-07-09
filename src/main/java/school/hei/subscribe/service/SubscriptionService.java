package school.hei.subscribe.service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import school.hei.subscribe.endpoint.event.EventProducer;
import school.hei.subscribe.endpoint.event.model.SubscriptionCreated;
import school.hei.subscribe.entity.Subscription;
import school.hei.subscribe.exception.AlreadySubscribedException;
import school.hei.subscribe.exception.ResourceNotFoundException;
import school.hei.subscribe.repository.*;

// Adapte "com.my.company.endpoint.event.EventProducer" au vrai package de ton projet POJA généré.
@Service
@AllArgsConstructor
public class SubscriptionService {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final EventProducer<SubscriptionCreated> eventProducer;

    @Transactional
    public Subscription subscribe(UUID userId, UUID courseId) {
        JUser user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("Utilisateur introuvable: " + userId));

        JCourse course = courseRepository.findById(courseId)
            .orElseThrow(() -> new ResourceNotFoundException("Cours introuvable: " + courseId));

        if (subscriptionRepository.existsByUserAndCourse(user, course)) {
            throw new AlreadySubscribedException(
                "L'utilisateur " + userId + " est déjà inscrit au cours " + courseId
            );
        }

        JSubscription entity = new JSubscription(user, course, Instant.now());
        JSubscription saved = subscriptionRepository.save(entity);

        // Traitement asynchrone géré par un worker POJA (queue),
        // l'email est envoyé sans bloquer la réponse HTTP.
        var event = SubscriptionCreated.builder()
            .subscriptionId(saved.getId())
            .userEmail(user.getEmail())
            .userFirstName(user.getFirstName())
            .courseTitle(course.getTitle())
            .subscribedAt(saved.getSubscribedAt())
            .build();
        eventProducer.accept(List.of(event));

        return new Subscription(saved.getId(), user.getId(), course.getId(), saved.getSubscribedAt());
    }
}
