package school.hei.subscribe.endpoint.rest.controller.health;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.hei.subscribe.endpoint.rest.dto.SubscribeRequest;
import school.hei.subscribe.endpoint.rest.dto.SubscriptionResponse;
import school.hei.subscribe.service.SubscriptionService;

@RestController
@AllArgsConstructor
@RequestMapping("/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping
    public ResponseEntity<SubscriptionResponse> subscribe(@Valid @RequestBody SubscribeRequest request) {
        var subscription = subscriptionService.subscribe(request.getUserId(), request.getCourseId());
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(new SubscriptionResponse(subscription));
    }
}
