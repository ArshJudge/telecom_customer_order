package ca.arshjudge.telecom.api.dto;
import jakarta.validation.constraints.NotNull;
public record SubscriptionRequest(@NotNull Long customerId, @NotNull Long planId) {}
