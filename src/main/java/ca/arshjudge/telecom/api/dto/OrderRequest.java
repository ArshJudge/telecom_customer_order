package ca.arshjudge.telecom.api.dto;
import jakarta.validation.constraints.*;
public record OrderRequest(@NotNull Long customerId, @NotNull Long planId, @Size(max = 500) String notes) {}
