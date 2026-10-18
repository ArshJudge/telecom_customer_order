package ca.arshjudge.telecom.domain;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "subscriptions", indexes = @Index(name = "idx_subscription_customer", columnList = "customer_id"))
public class Subscription {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false) @JoinColumn(name = "customer_id", nullable = false) private Customer customer;
    @ManyToOne(fetch = FetchType.EAGER, optional = false) @JoinColumn(name = "plan_id", nullable = false) private Plan plan;
    @Enumerated(EnumType.STRING) @Column(nullable = false, length = 20) private SubscriptionStatus status = SubscriptionStatus.ACTIVE;
    @Column(nullable = false, updatable = false) private Instant createdAt;

    protected Subscription() {}
    public Subscription(Customer customer, Plan plan) { this.customer = customer; this.plan = plan; }
    @PrePersist void onCreate() { createdAt = Instant.now(); }
    public Long getId() { return id; }
    public Customer getCustomer() { return customer; }
    public Plan getPlan() { return plan; }
    public SubscriptionStatus getStatus() { return status; }
    public Instant getCreatedAt() { return createdAt; }
    public void setStatus(SubscriptionStatus status) { this.status = status; }
}
