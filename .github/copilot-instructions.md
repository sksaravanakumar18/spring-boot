# Spring Boot Master Project - Copilot Instructions

<!-- Use this file to provide workspace-specific custom instructions to Copilot. For more details, visit https://code.visualstudio.com/docs/copilot/copilot-customization#_use-a-githubcopilotinstructionsmd-file -->

## Project Overview
This is a comprehensive Spring Boot learning project designed to demonstrate all core concepts and best practices. The project follows a layered architecture and implements industry-standard patterns.

## Architecture & Patterns
- **Layered Architecture**: Controller → Service → Repository → Entity
- **DTO Pattern**: Separate request/response objects from entities
- **Repository Pattern**: Data access abstraction with Spring Data JPA
- **Exception Handling**: Global exception handling with @RestControllerAdvice

## Code Style & Conventions
- Use meaningful variable and method names
- Follow Java naming conventions (camelCase for methods/variables, PascalCase for classes)
- Add comprehensive JavaDoc comments for public methods and classes
- Include validation annotations on DTOs
- Use proper HTTP status codes in REST endpoints
- Implement proper error handling with custom exceptions

## Spring Boot Concepts Covered
- **Auto-configuration**: Leverage Spring Boot's auto-configuration
- **Dependency Injection**: Use constructor injection (preferred) or @Autowired
- **Data JPA**: Entity relationships, custom queries, pagination
- **Security**: Authentication, authorization, password encoding
- **Caching**: Method-level caching with @Cacheable
- **Validation**: Bean validation with JSR-303 annotations
- **Testing**: Unit tests, integration tests, MockMvc
- **Profiles**: Environment-specific configurations
- **Actuator**: Monitoring and management endpoints

## Best Practices to Follow
- Always validate input data
- Use DTOs for API requests/responses
- Implement proper exception handling
- Add comprehensive logging
- Write unit and integration tests
- Use transactions appropriately
- Follow RESTful API conventions
- Document APIs with Swagger/OpenAPI
- Implement proper security measures
- Use profiles for different environments

## Development Guidelines
- Keep controllers thin - business logic in services
- Use repository interfaces for data access
- Implement proper pagination for list endpoints
- Use appropriate HTTP methods and status codes
- Add API documentation with Swagger annotations
- Implement caching where appropriate
- Use async processing for long-running operations
