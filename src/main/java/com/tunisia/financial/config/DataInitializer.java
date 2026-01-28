package com.tunisia.financial.config;

import com.tunisia.financial.entity.User;
import com.tunisia.financial.enums.UserRole;
import com.tunisia.financial.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Database initialization with sample data
 */
@Configuration
@RequiredArgsConstructor
@Slf4j
public class DataInitializer {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner initDatabase() {
        return args -> {
            // Check if data already exists
            if (userRepository.count() > 0) {
                log.info("Database already initialized");
                return;
            }

            log.info("Initializing database with sample data...");

            // Create Admin User
            User admin = User.builder()
                    .username("admin")
                    .email("admin@sesame.tn")
                    .password(passwordEncoder.encode("Admin@123"))
                    .fullName("System Administrator")
                    .role(UserRole.ADMIN)
                    .enabled(true)
                    .build();
            userRepository.save(admin);

            // Create Financial Analyst
            User analyst = User.builder()
                    .username("analyst")
                    .email("analyst@sesame.tn")
                    .password(passwordEncoder.encode("Analyst@123"))
                    .fullName("Financial Analyst")
                    .role(UserRole.FINANCIAL_ANALYST)
                    .enabled(true)
                    .build();
            userRepository.save(analyst);

            // Create SME User
            User smeUser = User.builder()
                    .username("smeuser")
                    .email("sme@sesame.tn")
                    .password(passwordEncoder.encode("Sme@123"))
                    .fullName("SME User")
                    .role(UserRole.SME_USER)
                    .enabled(true)
                    .build();
            userRepository.save(smeUser);

            // Create Auditor
            User auditor = User.builder()
                    .username("auditor")
                    .email("auditor@sesame.tn")
                    .password(passwordEncoder.encode("Auditor@123"))
                    .fullName("System Auditor")
                    .role(UserRole.AUDITOR)
                    .enabled(true)
                    .build();
            userRepository.save(auditor);

            log.info("Database initialized successfully!");
            log.info("Sample users created:");
            log.info("   Admin: admin@sesame.tn / Admin@123");
            log.info("   Analyst: analyst@sesame.tn / Analyst@123");
            log.info("   SME User: sme@sesame.tn / con");
            log.info("   Auditor: auditor@sesame.tn / Auditor@123");
        };
    }
}
