package com.goodReads.library.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Objects;

@Configuration
public class AppAuthenticationProvider implements AuthenticationProvider {// after spring security any request that comes spring will first check if there's any implementation
    // of AuthenticationProvider// * 2. accept/ retrieves username and password when the user inputs them by AuthenticationProvider class via Authentication object
    @Autowired
UserDetailsService userDetailsService;// we use interface UserDetailsService provided default by spring so we can use another implementation of MongoDB also with MySQl if needed in future
    @Autowired
    PasswordEncoder encoder;// by default the password encoder I'm using is injected here i.e. NoOpPasswordEncoder, even if I change the encoder in SecurityConfiguration in future
    // I don't have to change it in this class. As I'm injecting interface(PasswordEncoder).
@Override //Authentication object -> it will hold the details of the username and password which has been provided by the user.
public Authentication authenticate(Authentication authentication) throws AuthenticationException {// It is a method defined by the AuthenticationProvider interface, and its signature
    // is predefined by the interface, authentication method which we override. Authentication which is a bean/ object provided by spring security it will authenticate the user.
// Initially when the user enters the username it's stored as principal as some sites use number, email also as username. so all r principles and they have same pd so we
// use below line to set the username from name.
String username=authentication.getName(); // get the username from the authentication object
UserDetails userDetails=userDetailsService.loadUserByUsername(username);// get the user details from the database
if(Objects.nonNull(userDetails)){  // check if the user details is not null
if(encoder.matches(authentication.getCredentials().toString(),userDetails.getPassword())){// check if the password matches
return new UsernamePasswordAuthenticationToken(userDetails.getUsername(),userDetails.getPassword(),userDetails.getAuthorities());
// Username, password and authorities are needed to create a sessionId/token for us.
}
}
throw new BadCredentialsException("Invalid username or password");// if the username or password is not valid
}

@Override
public boolean supports(Class<?> authentication) {// check which authentication object like passwords, passkeys, token is supported
return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);// return true if the authentication object type i.e. using userName and password
// to authenticate the user is supported. checks if the provided authentication object is assignable from UsernamePasswordAuthenticationToken class.

// if i use below logic then it means that I support two different types of authentication types, Using userName, pd and JaasAuthenticationProvider i.e. Java Authentication token.
        /*if(UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication))//
            return true;
        if(JaasAuthenticationProvider.class.isAssignableFrom(authentication))
            return true;
        else
            return false;*/
    }
}
