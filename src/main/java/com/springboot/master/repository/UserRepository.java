package com.springboot.master.repository;

import com.springboot.master.entity.User;
import com.springboot.master.entity.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * User Repository Interface
 * 
 * Demonstrates Spring Data JPA concepts:
 * - Repository interface extending JpaRepository
 * - Query methods by naming convention
 * - Custom JPQL queries with @Query
 * - Method parameters binding
 * - Pagination support
 * - Optional return types
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Query methods by naming convention
    Optional<User> findByUsername(String username);
    
    Optional<User> findByEmail(String email);
    
    List<User> findByRole(UserRole role);
    
    List<User> findByIsActiveTrue();
    
    List<User> findByAgeBetween(Integer minAge, Integer maxAge);
    
    List<User> findByFirstNameContainingIgnoreCase(String firstName);
    
    Page<User> findByIsActiveTrue(Pageable pageable);
    
    // Custom JPQL queries
    @Query("SELECT u FROM User u WHERE u.age > :age")
    List<User> findUsersOlderThan(@Param("age") Integer age);
    
    @Query("SELECT u FROM User u WHERE u.firstName LIKE %:name% OR u.lastName LIKE %:name%")
    List<User> findByFirstNameOrLastNameContaining(@Param("name") String name);
    
    @Query("SELECT COUNT(u) FROM User u WHERE u.role = :role")
    Long countByRole(@Param("role") UserRole role);
    
    // Native SQL query
    @Query(value = "SELECT * FROM users u WHERE u.created_at >= DATE_SUB(NOW(), INTERVAL 30 DAY)", 
           nativeQuery = true)
    List<User> findUsersCreatedInLast30Days();
    
    // Custom query with Projection
    @Query("SELECT u.username, u.email, u.role FROM User u WHERE u.isActive = true")
    List<Object[]> findActiveUsersBasicInfo();
    
    // Exists query
    boolean existsByUsername(String username);
    
    boolean existsByEmail(String email);
}
