package ca.arshjudge.telecom.service;

import ca.arshjudge.telecom.api.dto.*;
import ca.arshjudge.telecom.domain.*;
import ca.arshjudge.telecom.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class OrderService {
    private final ServiceOrderRepository orders;
    private final CustomerRepository customers;
    private final PlanService plans;
    public OrderService(ServiceOrderRepository orders, CustomerRepository customers, PlanService plans) {
        this.orders = orders; this.customers = customers; this.plans = plans;
    }
    public OrderResponse create(OrderRequest request) {
        Customer customer = customers.findById(request.customerId()).orElseThrow(() -> new NotFoundException("Customer " + request.customerId() + " was not found"));
        Plan plan = plans.find(request.planId());
        if (!plan.isActive()) throw new ConflictException("Cannot order an inactive plan");
        return OrderResponse.from(orders.save(new ServiceOrder(customer, plan, request.notes())));
    }
    @Transactional(readOnly = true)
    public List<OrderResponse> list(Long customerId) {
        List<ServiceOrder> result = customerId == null ? orders.findAll() : orders.findByCustomerId(customerId);
        return result.stream().map(OrderResponse::from).toList();
    }
    public OrderResponse changeStatus(Long id, OrderStatus status) {
        ServiceOrder order = orders.findById(id).orElseThrow(() -> new NotFoundException("Order " + id + " was not found"));
        if (order.getStatus() == OrderStatus.CANCELLED || order.getStatus() == OrderStatus.COMPLETED) {
            throw new ConflictException("A terminal order cannot be changed");
        }
        order.setStatus(status);
        return OrderResponse.from(orders.save(order));
    }
}
