package com.springboot.master.service;

import com.springboot.master.dto.CreateUserDto;
import com.springboot.master.dto.UserResponseDto;
import com.springboot.master.entity.User;
import com.springboot.master.entity.UserRole;
import com.springboot.master.exception.ResourceNotFoundException;
import com.springboot.master.exception.DuplicateResourceException;
import com.springboot.master.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * User Service Class
 * 
 * Demonstrates Spring Boot service layer concepts:
 * - Service layer pattern with @Service
 * - Dependency injection with @Autowired
 * - Transaction management with @Transactional
 * - Caching with @Cacheable and @CacheEvict
 * - Exception handling
 * - DTO mapping
 * - Business logic separation
 */
@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Create a new user
     * Demonstrates: Input validation, password encoding, duplicate checking
     */
    public UserResponseDto createUser(CreateUserDto createUserDto) {
        // Check for duplicates
        if (userRepository.existsByUsername(createUserDto.getUsername())) {
            throw new DuplicateResourceException("Username already exists: " + createUserDto.getUsername());
        }
        
        if (userRepository.existsByEmail(createUserDto.getEmail())) {
            throw new DuplicateResourceException("Email already exists: " + createUserDto.getEmail());
        }

        // Create and save user
        User user = new User(
            createUserDto.getUsername(),
            createUserDto.getEmail(),
            passwordEncoder.encode(createUserDto.getPassword()),
            createUserDto.getFirstName(),
            createUserDto.getLastName(),
            createUserDto.getAge()
        );

        User savedUser = userRepository.save(user);
        return mapToResponseDto(savedUser);
    }

    /**
     * Get user by ID with caching
     * Demonstrates: Caching, exception handling
     */
    @Cacheable(value = "users", key = "#id")
    @Transactional(readOnly = true)
    public UserResponseDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return mapToResponseDto(user);
    }

    /**
     * Get all users with pagination
     * Demonstrates: Pagination, DTO mapping
     */
    @Transactional(readOnly = true)
    public Page<UserResponseDto> getAllUsers(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return users.map(this::mapToResponseDto);
    }

    /**
     * Get users by role
     * Demonstrates: Query methods, stream processing
     */
    @Transactional(readOnly = true)
    public List<UserResponseDto> getUsersByRole(UserRole role) {
        List<User> users = userRepository.findByRole(role);
        return users.stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    /**
     * Update user and evict cache
     * Demonstrates: Cache eviction, partial updates
     */
    @CacheEvict(value = "users", key = "#id")
    public UserResponseDto updateUser(Long id, CreateUserDto updateDto) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        // Update fields
        existingUser.setFirstName(updateDto.getFirstName());
        existingUser.setLastName(updateDto.getLastName());
        existingUser.setAge(updateDto.getAge());

        // Check for username/email conflicts only if they're being changed
        if (!existingUser.getUsername().equals(updateDto.getUsername())) {
            if (userRepository.existsByUsername(updateDto.getUsername())) {
                throw new DuplicateResourceException("Username already exists: " + updateDto.getUsername());
            }
            existingUser.setUsername(updateDto.getUsername());
        }

        if (!existingUser.getEmail().equals(updateDto.getEmail())) {
            if (userRepository.existsByEmail(updateDto.getEmail())) {
                throw new DuplicateResourceException("Email already exists: " + updateDto.getEmail());
            }
            existingUser.setEmail(updateDto.getEmail());
        }

        User savedUser = userRepository.save(existingUser);
        return mapToResponseDto(savedUser);
    }

    /**
     * Delete user and evict cache
     * Demonstrates: Cache eviction, soft delete option
     */
    @CacheEvict(value = "users", key = "#id")
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        
        // Soft delete - just deactivate the user
        user.setIsActive(false);
        userRepository.save(user);
        
        // For hard delete, use: userRepository.deleteById(id);
    }

    /**
     * Search users by name
     * Demonstrates: Custom query methods
     */
    @Transactional(readOnly = true)
    public List<UserResponseDto> searchUsersByName(String name) {
        List<User> users = userRepository.findByFirstNameOrLastNameContaining(name);
        return users.stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    /**
     * Get active users with pagination
     * Demonstrates: Conditional queries with pagination
     */
    @Transactional(readOnly = true)
    public Page<UserResponseDto> getActiveUsers(Pageable pageable) {
        Page<User> users = userRepository.findByIsActiveTrue(pageable);
        return users.map(this::mapToResponseDto);
    }

    /**
     * Private helper method to map Entity to DTO
     * Demonstrates: Entity to DTO mapping best practices
     */
    private UserResponseDto mapToResponseDto(User user) {
        return new UserResponseDto(
            user.getId(),
            user.getUsername(),
            user.getEmail(),
            user.getFirstName(),
            user.getLastName(),
            user.getAge(),
            user.getRole(),
            user.getIsActive(),
            user.getCreatedAt(),
            user.getUpdatedAt()
        );
    }
}
