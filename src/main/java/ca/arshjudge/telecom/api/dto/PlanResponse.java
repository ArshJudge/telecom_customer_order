package ca.arshjudge.telecom.api.dto;
import ca.arshjudge.telecom.domain.*;
import java.math.BigDecimal;
public record PlanResponse(Long id, String name, String description, PlanType type, BigDecimal monthlyPrice, boolean active) {
    public static PlanResponse from(Plan p) {
        return new PlanResponse(p.getId(), p.getName(), p.getDescription(), p.getType(), p.getMonthlyPrice(), p.isActive());
    }
}
