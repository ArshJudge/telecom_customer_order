package ca.arshjudge.telecom.domain;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "service_orders", indexes = @Index(name = "idx_order_customer", columnList = "customer_id"))
public class ServiceOrder {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false) @JoinColumn(name = "customer_id", nullable = false) private Customer customer;
    @ManyToOne(fetch = FetchType.LAZY, optional = false) @JoinColumn(name = "plan_id", nullable = false) private Plan plan;
    @Enumerated(EnumType.STRING) @Column(nullable = false, length = 20) private OrderStatus status = OrderStatus.CREATED;
    @Column(nullable = false, updatable = false) private Instant createdAt;
    @Column(length = 500) private String notes;

    protected ServiceOrder() {}
    public ServiceOrder(Customer customer, Plan plan, String notes) { this.customer = customer; this.plan = plan; this.notes = notes; }
    @PrePersist void onCreate() { createdAt = Instant.now(); }
    public Long getId() { return id; }
    public Customer getCustomer() { return customer; }
    public Plan getPlan() { return plan; }
    public OrderStatus getStatus() { return status; }
    public Instant getCreatedAt() { return createdAt; }
    public String getNotes() { return notes; }
    public void setStatus(OrderStatus status) { this.status = status; }
}
