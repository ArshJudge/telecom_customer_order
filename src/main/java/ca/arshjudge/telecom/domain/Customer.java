package ca.arshjudge.telecom.domain;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "customers", indexes = @Index(name = "idx_customer_email", columnList = "email", unique = true))
public class Customer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 80) private String firstName;
    @Column(nullable = false, length = 80) private String lastName;
    @Column(nullable = false, unique = true, length = 160) private String email;
    @Column(length = 24) private String phone;
    @Column(nullable = false, updatable = false) private Instant createdAt;

    protected Customer() {}
    public Customer(String firstName, String lastName, String email, String phone) {
        this.firstName = firstName; this.lastName = lastName; this.email = email; this.phone = phone;
    }
    @PrePersist void onCreate() { createdAt = Instant.now(); }
    public Long getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public Instant getCreatedAt() { return createdAt; }
    public void update(String firstName, String lastName, String email, String phone) {
        this.firstName = firstName; this.lastName = lastName; this.email = email; this.phone = phone;
    }
}
