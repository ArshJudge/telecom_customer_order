package ca.arshjudge.telecom.service;

import ca.arshjudge.telecom.api.dto.*;
import ca.arshjudge.telecom.domain.Customer;
import ca.arshjudge.telecom.repository.CustomerRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerService {
    private final CustomerRepository repository;
    public CustomerService(CustomerRepository repository) { this.repository = repository; }

    public CustomerResponse create(CustomerRequest request) {
        if (repository.existsByEmailIgnoreCase(request.email())) throw new ConflictException("A customer with this email already exists");
        Customer c = new Customer(request.firstName().trim(), request.lastName().trim(), request.email().trim().toLowerCase(), request.phone());
        return CustomerResponse.from(repository.save(c));
    }
    @Transactional(readOnly = true)
    public Page<CustomerResponse> list(int page, int size) {
        return repository.findAll(PageRequest.of(page, size, Sort.by("id").descending())).map(CustomerResponse::from);
    }
    @Transactional(readOnly = true)
    public CustomerResponse get(Long id) { return CustomerResponse.from(find(id)); }
    public CustomerResponse update(Long id, CustomerRequest request) {
        Customer c = find(id);
        repository.findByEmailIgnoreCase(request.email()).filter(existing -> !existing.getId().equals(id))
            .ifPresent(existing -> { throw new ConflictException("A customer with this email already exists"); });
        c.update(request.firstName().trim(), request.lastName().trim(), request.email().trim().toLowerCase(), request.phone());
        return CustomerResponse.from(repository.save(c));
    }
    public void delete(Long id) {
        Customer c = find(id);
        repository.delete(c);
    }
    private Customer find(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Customer " + id + " was not found"));
    }
}
