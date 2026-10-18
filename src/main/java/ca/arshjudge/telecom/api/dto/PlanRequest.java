package ca.arshjudge.telecom.api.dto;
import ca.arshjudge.telecom.domain.PlanType;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
public record PlanRequest(
    @NotBlank @Size(max = 100) String name,
    @NotBlank @Size(max = 400) String description,
    @NotNull PlanType type,
    @NotNull @DecimalMin("0.00") @Digits(integer = 8, fraction = 2) BigDecimal monthlyPrice
) {}
