package ca.arshjudge.telecom.service;

import ca.arshjudge.telecom.api.dto.*;
import ca.arshjudge.telecom.domain.*;
import ca.arshjudge.telecom.repository.PlanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class PlanService {
    private final PlanRepository repository;
    public PlanService(PlanRepository repository) { this.repository = repository; }
    public PlanResponse create(PlanRequest request) {
        if (repository.existsByNameIgnoreCase(request.name())) throw new ConflictException("A plan with this name already exists");
        return PlanResponse.from(repository.save(new Plan(request.name().trim(), request.description().trim(), request.type(), request.monthlyPrice())));
    }
    @Transactional(readOnly = true)
    public List<PlanResponse> list(boolean activeOnly) {
        return (activeOnly ? repository.findByActiveTrue() : repository.findAll()).stream().map(PlanResponse::from).toList();
    }
    @Transactional(readOnly = true)
    public Plan find(Long id) { return repository.findById(id).orElseThrow(() -> new NotFoundException("Plan " + id + " was not found")); }
    public PlanResponse update(Long id, PlanRequest request, boolean active) {
        Plan p = find(id);
        p.update(request.name().trim(), request.description().trim(), request.type(), request.monthlyPrice(), active);
        return PlanResponse.from(repository.save(p));
    }
}
