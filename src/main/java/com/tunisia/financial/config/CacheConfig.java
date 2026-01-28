package com.tunisia.financial.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {
    // Simple cache configuration
    // Can be extended with Redis or other caching providers
}
