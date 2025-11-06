package com.springboot.master.controller;

import com.springboot.master.dto.CreateUserDto;
import com.springboot.master.dto.UserResponseDto;
import com.springboot.master.entity.UserRole;
import com.springboot.master.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * User Controller Test
 * 
 * Demonstrates:
 * - @WebMvcTest for testing web layer
 * - MockMvc for testing HTTP endpoints
 * - @MockBean for mocking services
 * - Security testing with @WithMockUser
 * - JSON serialization/deserialization
 * - Request/Response testing
 */
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private CreateUserDto createUserDto;
    private UserResponseDto userResponseDto;

    @BeforeEach
    void setUp() {
        createUserDto = new CreateUserDto(
                "testuser",
                "test@example.com",
                "password123",
                "John",
                "Doe",
                25
        );

        userResponseDto = new UserResponseDto(
                1L,
                "testuser",
                "test@example.com",
                "John",
                "Doe",
                25,
                UserRole.USER,
                true,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    /**
     * Test creating a user - Success case
     */
    @Test
    @WithMockUser
    void createUser_Success() throws Exception {
        when(userService.createUser(any(CreateUserDto.class))).thenReturn(userResponseDto);

        mockMvc.perform(post("/users")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createUserDto)))
                .andExpect(status().isCreated())                .andExpect(jsonPath("$.username").value("testuser"))
                .andExpect(jsonPath("$.email").value("test@example.com"))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"));
    }

    /**
     * Test creating a user with validation errors
     */
    @Test
    @WithMockUser
    void createUser_ValidationError() throws Exception {
        CreateUserDto invalidDto = new CreateUserDto();
        invalidDto.setUsername(""); // Invalid - blank username

        mockMvc.perform(post("/users")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidDto)))
                .andExpect(status().isBadRequest());
    }

    /**
     * Test getting user by ID - Success case
     */
    @Test
    @WithMockUser
    void getUserById_Success() throws Exception {
        when(userService.getUserById(1L)).thenReturn(userResponseDto);

        mockMvc.perform(get("/users/1"))                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("testuser"));
    }

    /**
     * Test updating user - Success case
     */
    @Test
    @WithMockUser
    void updateUser_Success() throws Exception {
        when(userService.updateUser(eq(1L), any(CreateUserDto.class))).thenReturn(userResponseDto);

        mockMvc.perform(put("/users/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createUserDto)))                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("testuser"));
    }    /**
     * Test deleting user - Success case
     */
    @Test
    @WithMockUser
    void deleteUser_Success() throws Exception {
        mockMvc.perform(delete("/users/1")
                        .with(csrf()))
                .andExpect(status().isNoContent());
    }

    /**
     * Test health check endpoint
     */
    @Test
    @WithMockUser // Add authentication context for the test
    void healthCheck_Success() throws Exception {
        mockMvc.perform(get("/users/health"))
                .andExpect(status().isOk())
                .andExpect(content().string("User service is running!"));
    }
}
