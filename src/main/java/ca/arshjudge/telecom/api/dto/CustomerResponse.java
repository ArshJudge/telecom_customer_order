package ca.arshjudge.telecom.api.dto;
import ca.arshjudge.telecom.domain.Customer;
import java.time.Instant;
public record CustomerResponse(Long id, String firstName, String lastName, String email, String phone, Instant createdAt) {
    public static CustomerResponse from(Customer c) {
        return new CustomerResponse(c.getId(), c.getFirstName(), c.getLastName(), c.getEmail(), c.getPhone(), c.getCreatedAt());
    }
}
