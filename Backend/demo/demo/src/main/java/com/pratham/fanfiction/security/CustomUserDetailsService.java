package com.pratham.fanfiction.security;

//import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pratham.fanfiction.sql.AppUserEntity;
import com.pratham.fanfiction.sql.AppUserEntityRepository;

import static org.springframework.security.core.userdetails.User.withUsername;


@Service
@Scope("singleton") // explicit
public class CustomUserDetailsService implements UserDetailsService 
{

	
	
	//Represents your mongoDb interface which helps to fetch users
	// This is no longer in USE
    @Autowired
    private AppUserRepository repo;

    
    @Autowired 
    private AppUserEntityRepository repoSQL;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
    {
    	
    	//oLD CODE WITH MongoDb
//        AppUser user = repo.findById(username).orElseThrow(() -> new UsernameNotFoundException("No user"));
        
    	
    	//New code with SQL
        AppUserEntity user = repoSQL.findByUsername(username);
        
        
        //Returns a username fetched by the database
        return User.withUsername(user.getUsername())
                   .password(user.getPasswordHash())
                   .authorities(user.getRole())
                   .build();
    }
}
