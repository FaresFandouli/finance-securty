package com.tunisia.financial.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("AI-Powered Financial Intelligence System API")
                        .version("1.0.0")
                        .description("""
                                Complete REST API for AI-powered fraud detection, health monitoring, 
                                and risk assessment system for Tunisian SMEs.
                                
                                ## Features:
                                - 🤖 **AI Fraud Detection** (DJL + ONNX)
                                - 🏥 **Health Monitoring** with AI analysis
                                - 📊 **Credit Risk Assessment**
                                - 🔐 **JWT Authentication**
                                - 🛡️ **Role-Based Access Control**
                                
                                ## Authentication:
                                1. Register a user: `POST /api/auth/register`
                                2. Login: `POST /api/auth/login`
                                3. Copy the JWT token from response
                                4. Click **Authorize** button above
                                5. Enter: `Bearer YOUR_TOKEN_HERE`
                                
                                ## Roles:
                                - **ADMIN**: Full access to all endpoints
                                - **FINANCIAL_ANALYST**: Fraud detection + Reports
                                - **AUDITOR**: Fraud detection only
                                - **SME_USER**: Limited access
                                """)
                        .contact(new Contact()
                                .name("Sesame University - Secure Programming")
                                .email("chaouki.bayoudhi@sesame.com.tn")
                                .url("https://sesame.com.tn"))
                        .license(new License()
                                .name("Academic Project")
                                .url("https://sesame.com.tn")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Local Development Server"),
                        new Server()
                                .url("https://api.financial-ai.com")
                                .description("Production Server (if deployed)")))
                .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
                .components(new Components()
                        .addSecuritySchemes("Bearer Authentication",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .description("Enter JWT token from /api/auth/login")));
    }
}