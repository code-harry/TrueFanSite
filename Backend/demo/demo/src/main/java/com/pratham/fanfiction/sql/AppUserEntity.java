package com.pratham.fanfiction.sql;



import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")  // MySQL table name
public class AppUserEntity 
{

    @Id
    private String username; // primary key

    private String passwordHash;

    private String role;

    private int tokenVersion = 0;

    // Getters and Setters
    public String getUsername() 
    {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public int getTokenVersion() {
        return tokenVersion;
    }
    public void setTokenVersion(int tokenVersion) {
        this.tokenVersion = tokenVersion;
    }
}
