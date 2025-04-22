package com.hospital.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("file:.env")
public class EnvConfig {    
    @Value("${DB_URL}")
    private String dbUrl;
    
    @Value("${DB_USERNAME}")
    private String dbUsername;
    
    @Value("${DB_PASSWORD}")
    private String dbPassword;
    
    @Value("${SERVER_PORT}")
    private int serverPort;
    
    @Value("${SPRING_PROFILES_ACTIVE}")
    private String activeProfile;
    
    // Getters
    public String getDbUrl() {
        return dbUrl;
    }

    public String getDbUsername() {
        return dbUsername;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public int getServerPort() {
        return serverPort;
    }

    public String getActiveProfile() {
        return activeProfile;
    }
}