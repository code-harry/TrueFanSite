package com.pratham.fanfiction.sql;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserEntityRepository extends JpaRepository<AppUserEntity, String> {
    // Optional custom query methods
    AppUserEntity findByUsername(String username);
}