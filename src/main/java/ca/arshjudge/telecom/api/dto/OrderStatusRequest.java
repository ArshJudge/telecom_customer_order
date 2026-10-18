package ca.arshjudge.telecom.api.dto;
import ca.arshjudge.telecom.domain.OrderStatus;
import jakarta.validation.constraints.NotNull;
public record OrderStatusRequest(@NotNull OrderStatus status) {}
