package ca.arshjudge.telecom.service;

import ca.arshjudge.telecom.api.dto.*;
import ca.arshjudge.telecom.domain.*;
import ca.arshjudge.telecom.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class SubscriptionService {
    private final SubscriptionRepository subscriptions;
    private final CustomerRepository customers;
    private final PlanService plans;
    public SubscriptionService(SubscriptionRepository subscriptions, CustomerRepository customers, PlanService plans) {
        this.subscriptions = subscriptions; this.customers = customers; this.plans = plans;
    }
    public SubscriptionResponse create(SubscriptionRequest request) {
        Customer customer = customers.findById(request.customerId()).orElseThrow(() -> new NotFoundException("Customer " + request.customerId() + " was not found"));
        Plan plan = plans.find(request.planId());
        if (!plan.isActive()) throw new ConflictException("Cannot subscribe to an inactive plan");
        return SubscriptionResponse.from(subscriptions.save(new Subscription(customer, plan)));
    }
    @Transactional(readOnly = true)
    public List<SubscriptionResponse> list(Long customerId) {
        List<Subscription> result = customerId == null ? subscriptions.findAll() : subscriptions.findByCustomerId(customerId);
        return result.stream().map(SubscriptionResponse::from).toList();
    }
    public SubscriptionResponse changeStatus(Long id, SubscriptionStatus status) {
        Subscription s = subscriptions.findById(id).orElseThrow(() -> new NotFoundException("Subscription " + id + " was not found"));
        s.setStatus(status);
        return SubscriptionResponse.from(subscriptions.save(s));
    }
}
