package com.pratham.fanfiction.security;

import java.util.Optional;

//AppUserRepository
import org.springframework.data.mongodb.repository.MongoRepository;


// This class is used to find users from the database

// Below we will only use the methods to find by username and password but the rest are for the sake of only testing.
public interface AppUserRepository extends MongoRepository<AppUser, String> 
{
 // findById(username) already provided
	
	// To find by only username
	Optional<AppUser> findByUsername(String username);

	
	
	
	// Find by password
    Optional<AppUser> findByPasswordHash(String password);

    // Find by token number
    Optional<AppUser> findByTokenVersion(String tokenNumber);

    // Find by username AND password
    Optional<AppUser> findByUsernameAndPasswordHash(String username, String password);

    // Find by username AND token number
    Optional<AppUser> findByUsernameAndTokenVersion(String username, String tokenNumber);
    
    
 // Find by password AND token number
    Optional<AppUser> findByPasswordHashAndTokenVersion(String password, String tokenNumber);

    
    // Finding member by all three fields- namely password, hash and username
    Optional<AppUser> findByUsernameAndPasswordHashAndTokenVersion(String username, String password, String tokenNumber);

}
