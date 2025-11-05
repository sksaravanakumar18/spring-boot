package com.springboot.master.repository;

import com.springboot.master.entity.Post;
import com.springboot.master.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Post Repository Interface
 * 
 * Demonstrates advanced Spring Data JPA concepts:
 * - Complex query methods
 * - Relationship-based queries
 * - Date/time queries
 * - Pagination with custom queries
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    // Query methods by naming convention
    List<Post> findByUser(User user);
    
    List<Post> findByUserId(Long userId);
    
    List<Post> findByIsPublishedTrue();
    
    List<Post> findByTitleContainingIgnoreCase(String title);
    
    Page<Post> findByIsPublishedTrue(Pageable pageable);
    
    List<Post> findByCreatedAtAfter(LocalDateTime date);
    
    // Custom queries
    @Query("SELECT p FROM Post p WHERE p.user.id = :userId AND p.isPublished = true")
    List<Post> findPublishedPostsByUserId(@Param("userId") Long userId);
    
    @Query("SELECT p FROM Post p WHERE p.title LIKE %:keyword% OR p.content LIKE %:keyword%")
    List<Post> findByTitleOrContentContaining(@Param("keyword") String keyword);
    
    @Query("SELECT COUNT(p) FROM Post p WHERE p.user.id = :userId")
    Long countPostsByUserId(@Param("userId") Long userId);
    
    @Query("SELECT p FROM Post p WHERE p.createdAt BETWEEN :startDate AND :endDate")
    List<Post> findPostsCreatedBetween(
        @Param("startDate") LocalDateTime startDate, 
        @Param("endDate") LocalDateTime endDate
    );
}
