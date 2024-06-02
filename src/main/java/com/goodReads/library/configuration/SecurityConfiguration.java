package com.goodReads.library.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class   SecurityConfiguration {

//@Bean
//public PasswordEncoder passwordEncoder(){// * 4. Compare the password hash of the user. -> AuthenticationProvider -> PasswordEncoder.
//return NoOpPasswordEncoder.getInstance();// It is a no-operation password encoder that does not actually encode the password.It is used as a placeholder or for testing purposes where
//// the password is not actually encrypted. Once testing is done we use other techniques like BCrypt. getInstance() is a static factory method provided by NoOpPasswordEncoder.
////It returns the singleton instance of NoOpPasswordEncoder
//}

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean// at the start of the app it gets loaded it's
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{//5. Check if the user has authority on API i.e.check the SecurityFilterChain
// the object which we work on is httpSecurity. CSRF token is a security measure used to prevent Cross-Site Request Forgery attacks by validating the authenticity of requests.
        httpSecurity.cors(Customizer.withDefaults()).csrf(c->c.disable()).authorizeHttpRequests(authorize -> // authorizeHttpRequests() is used to authorize all my Http requests
                        authorize
//httpSecurity.csrf(csrf->csrf.disable());
//
//        httpSecurity.authorizeHttpRequests(authorize -> // authorizeHttpRequests() is used to authorize all my Http requests
//authorize// .csrf(c->c.disable()) disabling it because the postman cannot send csrf token whereas a browser can send csrf to backend app, instead of disable we can
// also configure that allow from this origin or that server, or don't allow from this server like that. Usually from the backend we disable only cors  and allow only
// the domain of React server so that if any other sites try to request, they get error

//cross-Origin Resource Sharing (CORS) for your HTTP security, method applies default CORS configuration settings. This ensures that your server can safely interact
// with web pages hosted on different domains.Customizer.withDefaults This configuration applies default CSRF protection provided by Spring
// Cross-Site Request Forgery (CSRF) protection for your HTTP security.applies default CSRF configuration settings,
// which involve adding CSRF tokens to forms and verifying those tokens on form submission.
// CSRF protection helps prevent attackers from performing unauthorized actions on behalf of authenticated users. To include the CSRF token in each request, you typically
// need to obtain the token from your application's frontend and include it in the request headers or body. But as ours don't have front end we disable it.
   //    .requestMatchers("**").permitAll()//"**" pattern is a wildcard this configuration applies security rules to all requests. so it permits all requests
       // .requestMatchers("/csrf").permitAll()// it will give the csrf token which can saved to be used in the future.
        .requestMatchers("/admin/**").hasAuthority("ADMIN")
        .requestMatchers("/greet/**").hasAuthority("USER")
        .requestMatchers("/admin/**").hasAuthority("ADMIN")// if the url has admin followed by anything to access that they need Admin authority
 .requestMatchers("/book/**").hasAuthority("USER")// .antMatchers("/book/**").hasAnyAuthority("ADMIN", "USER") .antMatchers() is primarily used for matching URL patterns,
        .requestMatchers("/user/**").hasAuthority("USER")//while .requestMatchers() is used for defining more flexible request matching criteria beyond just URL patterns.
//hasAnyAuthority() This configuration allows users with either "ADMIN" or "USER" authority to access URLs under "/admin/"
        .requestMatchers("/register").permitAll()// to access signup, we don't need authentication/ authorization
        .requestMatchers("/login").permitAll()
   .requestMatchers("/success").permitAll()
                                .requestMatchers("/book").permitAll()
        .requestMatchers("/error").permitAll()// if we don't permit this, when error occurs it shows 401 unauthorized but if we handled the exsception using
        .anyRequest().authenticated() // for any of these requests authentication is required
).formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults()).oauth2Login(Customizer.withDefaults());//telling to only authenticate access via httpBasic i.e. through postman i.e. response coming via API,
// .formLogin gives the html login form to
return httpSecurity.build();// we are using build editor, Builds and returns the configured httpSecurity object. In Spring Security, when you configure security settings using the
// httpSecurity object, you typically don't need to explicitly call build() at the end of the configuration. The httpSecurity object itself holds the configuration and is automatically applied by Spring Security to filter incoming HTTP requests.
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