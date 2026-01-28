package com.tunisia.financial.service;

import com.tunisia.financial.dto.request.LoginRequest;
import com.tunisia.financial.dto.request.UserRegistrationRequest;
import com.tunisia.financial.dto.response.AuthResponse;
import com.tunisia.financial.dto.response.UserResponse;

public interface AuthService {
    AuthResponse register(UserRegistrationRequest request);
    AuthResponse login(LoginRequest request);
    UserResponse getCurrentUser(String email);
}
