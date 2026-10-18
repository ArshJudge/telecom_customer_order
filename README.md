# Telecom Customer & Order Platform

A portfolio project demonstrating enterprise-style Java backend development with Spring Boot. It models telecom customers, service plans, subscriptions, and service orders.

> This is a personal portfolio project with illustrative data, not a production telecom system.

## Technology stack

- Java 21, Spring Boot 3.5
- Spring Web, Spring Data JPA, Bean Validation
- PostgreSQL for local containerized deployment; H2 for a quick local run and tests
- OpenAPI / Swagger UI
- JUnit 5 and Spring Boot Test
- Docker Compose and GitHub Actions CI

## Capabilities

- Customer CRUD with email uniqueness validation and paginated listing
- Service plan management, active-plan filtering, and pricing validation
- Subscription creation and lifecycle status updates
- Service order creation, customer filtering, and guarded terminal-state transitions
- Consistent JSON error responses for validation, missing resources, and conflicts
- Layered structure: API controllers, DTOs, services, repositories, and domain entities

## Run locally (Java 21 + Maven)

```bash
mvn spring-boot:run
```

The default profile uses an in-memory H2 database. The schema is generated automatically for local development.

## Run with Docker Compose (PostgreSQL)

```bash
docker compose up --build
```

The API is available at `http://localhost:8080`. Stop with `Ctrl+C`, then optionally run `docker compose down`.

The credentials in `docker-compose.yml` are local-development-only. Do not reuse them in a hosted environment.

## API documentation

- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

## API overview

| Method | Endpoint | Purpose |
|---|---|---|
| POST | `/api/v1/customers` | Create customer |
| GET | `/api/v1/customers?page=0&size=20` | List customers |
| GET | `/api/v1/customers/{id}` | Get customer |
| PUT | `/api/v1/customers/{id}` | Update customer |
| DELETE | `/api/v1/customers/{id}` | Delete customer |
| POST | `/api/v1/plans` | Create plan |
| GET | `/api/v1/plans?activeOnly=true` | List plans |
| PUT | `/api/v1/plans/{id}?active=true` | Update plan |
| POST | `/api/v1/subscriptions` | Subscribe customer to plan |
| GET | `/api/v1/subscriptions?customerId=1` | List subscriptions |
| PATCH | `/api/v1/subscriptions/{id}/status?status=SUSPENDED` | Change subscription status |
| POST | `/api/v1/orders` | Create service order |
| GET | `/api/v1/orders?customerId=1` | List orders |
| PATCH | `/api/v1/orders/{id}/status` | Change order status |

## Example requests

Create a customer:

```bash
curl -X POST http://localhost:8080/api/v1/customers \
  -H "Content-Type: application/json" \
  -d '{"firstName":"Avery","lastName":"Shah","email":"avery.shah@example.com","phone":"+1-555-0100"}'
```

Create a plan:

```bash
curl -X POST http://localhost:8080/api/v1/plans \
  -H "Content-Type: application/json" \
  -d '{"name":"Unlimited 5G","description":"Unlimited data plan with 5G access","type":"MOBILE","monthlyPrice":65.00}'
```

Create a subscription (replace IDs with returned values):

```bash
curl -X POST http://localhost:8080/api/v1/subscriptions \
  -H "Content-Type: application/json" \
  -d '{"customerId":1,"planId":1}'
```

Create an order:

```bash
curl -X POST http://localhost:8080/api/v1/orders \
  -H "Content-Type: application/json" \
  -d '{"customerId":1,"planId":1,"notes":"New mobile service activation"}'
```

## Configuration

The application supports environment variables for `SPRING_DATASOURCE_URL`, `SPRING_DATASOURCE_USERNAME`, `SPRING_DATASOURCE_PASSWORD`, `SPRING_DATASOURCE_DRIVER`, `SPRING_JPA_HIBERNATE_DDL_AUTO`, and `PORT`.

For production use, replace `ddl-auto=update` with database migrations (Flyway or Liquibase), use managed secrets, add authentication/authorization, and introduce observability and deployment-specific security hardening.

## Design notes / next steps

Potential follow-on enhancements:
- Add Flyway migrations and Testcontainers-based integration tests
- Add role-based authorization and audit history
- Add outbox pattern and Kafka events for order lifecycle changes
- Add Redis caching for catalog reads
- Add structured logs, metrics, tracing, and rate limiting

These are documented extension points, not implemented features.
