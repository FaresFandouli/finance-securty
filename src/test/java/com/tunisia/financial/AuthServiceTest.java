package com.tunisia.financial;

import com.tunisia.financial.dto.request.LoginRequest;
import com.tunisia.financial.dto.request.UserRegistrationRequest;
import com.tunisia.financial.dto.response.AuthResponse;
import com.tunisia.financial.entity.User;
import com.tunisia.financial.enums.UserRole;
import com.tunisia.financial.repository.UserRepository;
import com.tunisia.financial.service.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class AuthServiceTest {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void testUserRegistration() {
        UserRegistrationRequest request = new UserRegistrationRequest(
                "testuser",
                "test@test.com",
                "Test@1234",
                "Test User",
                UserRole.SME_USER
        );

        AuthResponse response = authService.register(request);

        assertNotNull(response);
        assertNotNull(response.accessToken());
        assertNotNull(response.user());
        assertEquals("testuser", response.user().username());
    }

    @Test
    void testUserLogin() {
        // First register a user
        UserRegistrationRequest regRequest = new UserRegistrationRequest(
                "logintest",
                "login@test.com",
                "Test@1234",
                "Login Test",
                UserRole.SME_USER
        );
        authService.register(regRequest);

        // Then login
        LoginRequest loginRequest = new LoginRequest("login@test.com", "Test@1234");
        AuthResponse response = authService.login(loginRequest);

        assertNotNull(response);
        assertNotNull(response.accessToken());
        assertEquals("login@test.com", response.user().email());
    }
}
