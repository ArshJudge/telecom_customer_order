package ca.arshjudge.telecom.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "plans")
public class Plan {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false, unique = true, length = 100) private String name;
    @Column(nullable = false, length = 400) private String description;
    @Enumerated(EnumType.STRING) @Column(nullable = false, length = 30) private PlanType type;
    @Column(nullable = false, precision = 10, scale = 2) private BigDecimal monthlyPrice;
    @Column(nullable = false) private boolean active = true;

    protected Plan() {}
    public Plan(String name, String description, PlanType type, BigDecimal monthlyPrice) {
        this.name = name; this.description = description; this.type = type; this.monthlyPrice = monthlyPrice;
    }
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public PlanType getType() { return type; }
    public BigDecimal getMonthlyPrice() { return monthlyPrice; }
    public boolean isActive() { return active; }
    public void update(String name, String description, PlanType type, BigDecimal monthlyPrice, boolean active) {
        this.name = name; this.description = description; this.type = type; this.monthlyPrice = monthlyPrice; this.active = active;
    }
}
