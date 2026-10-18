# Architecture

```mermaid
flowchart LR
  Client[API Client / Swagger UI] --> Controllers[REST Controllers]
  Controllers --> DTO[Request Validation and DTOs]
  DTO --> Services[Business Services]
  Services --> Repositories[Spring Data JPA Repositories]
  Repositories --> DB[(H2 or PostgreSQL)]
  Controllers --> Errors[Central Exception Handler]
```

## Domain relationships

- A customer can have multiple subscriptions.
- A customer can place multiple service orders.
- Each subscription references one plan.
- Each service order references one plan.
- Inactive plans cannot be selected for new subscriptions or orders.
- Completed and cancelled orders are terminal states in the current workflow.
