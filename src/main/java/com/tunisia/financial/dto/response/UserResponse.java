package com.tunisia.financial.dto.response;

import com.tunisia.financial.enums.UserRole;
import java.time.LocalDateTime;

public record UserResponse(
        Long id,
        String username,
        String email,
        String fullName,
        UserRole role,
        boolean enabled,
        LocalDateTime createdAt
) {}
