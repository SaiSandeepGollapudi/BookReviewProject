package com.goodReads.library.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class   SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder(){// * 4. Compare the password hash of the user. -> AuthenticationProvider -> PasswordEncoder.
        return NoOpPasswordEncoder.getInstance();// It is a no-operation password encoder that does not actually encode the password.
        // It is used as a placeholder or for testing purposes where the password is not actually encrypted. Once testing is done we use other techniques like BCrypt.
    }

    @Bean// at the start of the app it gets loaded it's
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{// * 5. Check if the user has authority on API i.e. check the a -> SecurityFilterChain
// the object which we work on is httpSecurity
        httpSecurity.cors(Customizer.withDefaults()).csrf(c->c.disable()).authorizeHttpRequests(authorize -> // authorizeHttpRequests() is used to authorize all my Http requests
authorize//cross-Origin Resource Sharing (CORS) for your HTTP security, method applies default CORS configuration settings.
// This ensures that your server can safely interact with web pages hosted on different domains.
// Cross-Site Request Forgery (CSRF)  protection for your HTTP security.applies default CSRF configuration settings,
// which involve adding CSRF tokens to forms and verifying those tokens on form submission.
// CSRF protection helps prevent attackers from performing unauthorized actions on behalf of authenticated users. To include the CSRF token in each request, you typically need to obtain
// the token from your application's frontend and include it in the request headers or body. But as ours don't have front end we disable it.
.requestMatchers("/admin/**").hasAuthority("ADMIN")// if the url has admin followed by anything to access that they need Admin authority
 .requestMatchers("/book/**").hasAuthority("USER")
        .requestMatchers("/user/**").hasAuthority("USER")
.requestMatchers("/signup").permitAll()// to access signup, we don't need authentication/ authorization
.anyRequest().authenticated() // for any of these requests authentication is required
).formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults());// telling to only allow access via httpBasic i.e. response coming via API,
// .formLogin gives the html login form
return httpSecurity.build();// we are using build editor
//  http://localhost:8080/admin/greet/saig now if you use user credentials for this it says 403 authenticated but not authorized but it works for this
//http://localhost:8080/greet/saig if you use user credentials
}}
/**
 * Encode -> transform of data into one from another format. which can be reversible easily
 *
 * hashing -> transform of data into non-consumable format which is non reversible and it ensures that same hash
 *              will be generated for same values always until the under-laying logic changes.
 *              algorithm  -> SHA-256
 *
 * encryption -> transform of data into non-consumable format and can be converted to original via decryption process.

 * Secure our APIs
 *
 * 1. Onboard the user with credentials. -> user object -> UserDetail.
 * 2. accept username and password from user. -> AuthenticationProvider via Authentication object
 * 3. Fetch the user from the databases -> UserDetailService Implementation
 * 4. Compare the password hash of the user. -> AuthenticationProvider -> PasswordEncoder.
 * 5. Check if the user has authority on API i.e. check the a -> SecurityFilterChain
 * 6. Let the user use the API.-> AuthenticationProvider
 *
 * */