package ca.arshjudge.telecom.api.dto;
import ca.arshjudge.telecom.domain.*;
import java.time.Instant;
public record SubscriptionResponse(Long id, Long customerId, Long planId, String planName, SubscriptionStatus status, Instant createdAt) {
    public static SubscriptionResponse from(Subscription s) {
        return new SubscriptionResponse(s.getId(), s.getCustomer().getId(), s.getPlan().getId(), s.getPlan().getName(), s.getStatus(), s.getCreatedAt());
    }
}
