package com.springboot.master.controller;

import com.springboot.master.dto.CreateUserDto;
import com.springboot.master.dto.UserResponseDto;
import com.springboot.master.entity.UserRole;
import com.springboot.master.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * User Controller - REST API endpoints
 * 
 * Demonstrates Spring Boot REST API concepts:
 * - @RestController for REST endpoints
 * - HTTP method mappings (@GetMapping, @PostMapping, etc.)
 * - Request/Response handling
 * - Validation with @Valid
 * - Path variables and request parameters
 * - HTTP status codes
 * - API documentation with Swagger
 * - Pagination and sorting
 */
@RestController
@RequestMapping("/users")
@Tag(name = "User Management", description = "APIs for managing users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Create a new user
     * Demonstrates: POST mapping, request body validation, HTTP status codes
     */
    @PostMapping
    @Operation(summary = "Create a new user", description = "Creates a new user with the provided information")
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody CreateUserDto createUserDto) {
        UserResponseDto createdUser = userService.createUser(createUserDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    /**
     * Get user by ID
     * Demonstrates: GET mapping, path variables, exception handling
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID", description = "Retrieves a user by their unique identifier")
    public ResponseEntity<UserResponseDto> getUserById(
            @Parameter(description = "User ID") @PathVariable Long id) {
        UserResponseDto user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    /**
     * Get all users with pagination
     * Demonstrates: Pagination, sorting, request parameters
     */
    @GetMapping
    @Operation(summary = "Get all users", description = "Retrieves all users with pagination and sorting")
    public ResponseEntity<Page<UserResponseDto>> getAllUsers(
            @Parameter(description = "Page number (0-based)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "10") int size,
            @Parameter(description = "Sort field") @RequestParam(defaultValue = "id") String sortBy,
            @Parameter(description = "Sort direction") @RequestParam(defaultValue = "ASC") String sortDirection) {
        
        Sort.Direction direction = Sort.Direction.fromString(sortDirection);
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<UserResponseDto> users = userService.getAllUsers(pageable);
        return ResponseEntity.ok(users);
    }

    /**
     * Get users by role
     * Demonstrates: Request parameters, enum handling
     */
    @GetMapping("/role/{role}")
    @Operation(summary = "Get users by role", description = "Retrieves users with a specific role")
    public ResponseEntity<List<UserResponseDto>> getUsersByRole(
            @Parameter(description = "User role") @PathVariable UserRole role) {
        List<UserResponseDto> users = userService.getUsersByRole(role);
        return ResponseEntity.ok(users);
    }

    /**
     * Search users by name
     * Demonstrates: Query parameters, search functionality
     */
    @GetMapping("/search")
    @Operation(summary = "Search users by name", description = "Searches users by first name or last name")
    public ResponseEntity<List<UserResponseDto>> searchUsers(
            @Parameter(description = "Search term") @RequestParam String name) {
        List<UserResponseDto> users = userService.searchUsersByName(name);
        return ResponseEntity.ok(users);
    }

    /**
     * Get active users with pagination
     * Demonstrates: Combined filtering and pagination
     */
    @GetMapping("/active")
    @Operation(summary = "Get active users", description = "Retrieves only active users with pagination")
    public ResponseEntity<Page<UserResponseDto>> getActiveUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<UserResponseDto> users = userService.getActiveUsers(pageable);
        return ResponseEntity.ok(users);
    }

    /**
     * Update user
     * Demonstrates: PUT mapping, path variables, request body
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update user", description = "Updates an existing user's information")
    public ResponseEntity<UserResponseDto> updateUser(
            @Parameter(description = "User ID") @PathVariable Long id,
            @Valid @RequestBody CreateUserDto updateDto) {
        UserResponseDto updatedUser = userService.updateUser(id, updateDto);
        return ResponseEntity.ok(updatedUser);
    }

    /**
     * Delete user
     * Demonstrates: DELETE mapping, void responses
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user", description = "Soft deletes a user (sets inactive)")
    public ResponseEntity<Void> deleteUser(
            @Parameter(description = "User ID") @PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Health check endpoint for users
     * Demonstrates: Simple GET endpoint, basic response
     */
    @GetMapping("/health")
    @Operation(summary = "User service health check", description = "Checks if the user service is running")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("User service is running!");
    }
}
