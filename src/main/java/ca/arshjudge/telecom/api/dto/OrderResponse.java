package ca.arshjudge.telecom.api.dto;
import ca.arshjudge.telecom.domain.*;
import java.time.Instant;
public record OrderResponse(Long id, Long customerId, Long planId, String planName, OrderStatus status, Instant createdAt, String notes) {
    public static OrderResponse from(ServiceOrder o) {
        return new OrderResponse(o.getId(), o.getCustomer().getId(), o.getPlan().getId(), o.getPlan().getName(), o.getStatus(), o.getCreatedAt(), o.getNotes());
    }
}
