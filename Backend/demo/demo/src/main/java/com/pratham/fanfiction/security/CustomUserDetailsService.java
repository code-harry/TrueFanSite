package com.pratham.fanfiction.security;

//import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import static org.springframework.security.core.userdetails.User.withUsername;


@Service
public class CustomUserDetailsService implements UserDetailsService 
{

	
	
	//Represents your mongoDb interface which helps to fetch users
    @Autowired
    private AppUserRepository repo;

    
    
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
    {
        AppUser user = repo.findById(username).orElseThrow(() -> new UsernameNotFoundException("No user"));
        
        
        //Returns a username fetched by the database
        return User.withUsername(user.getUsername())
                   .password(user.getPasswordHash())
                   .authorities(user.getRole())
                   .build();
    }
}
